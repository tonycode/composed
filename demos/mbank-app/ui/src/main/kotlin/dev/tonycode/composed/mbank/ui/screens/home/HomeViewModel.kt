package dev.tonycode.composed.mbank.ui.screens.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.tonycode.composed.mbank.domain.entity.AccountStats
import dev.tonycode.composed.mbank.domain.entity.AccountSummary
import dev.tonycode.composed.mbank.domain.entity.Transaction
import dev.tonycode.composed.mbank.domain.entity.UserProfile
import dev.tonycode.composed.mbank.domain.usecase.account.GetAccountStatsUsecase
import dev.tonycode.composed.mbank.domain.usecase.account.GetAccountSummaryUsecase
import dev.tonycode.composed.mbank.domain.usecase.account.GetAccountTransactionsUsecase
import dev.tonycode.composed.mbank.domain.usecase.user.GetAuthorizedUserIdUsecase
import dev.tonycode.composed.mbank.domain.usecase.user.GetUserProfileUsecase
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val MAX_RECENT_TRANSACTIONS = 4

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAuthorizedUserIdUsecase: GetAuthorizedUserIdUsecase,
    private val getUserProfileUsecase: GetUserProfileUsecase,
    private val getAccountSummaryUsecase: GetAccountSummaryUsecase,
    private val getAccountStatsUsecase: GetAccountStatsUsecase,
    private val getAccountTransactions: GetAccountTransactionsUsecase,
) : ViewModel() {
    // user
    private val _userProfile = mutableStateOf<UserProfile?>(null)

    val userProfile: State<UserProfile?>
        get() = _userProfile

    // balance overview
    private val _accountSummary = mutableStateOf<AccountSummary?>(null)

    val accountSummary: State<AccountSummary?>
        get() = _accountSummary

    private val _accountStats = mutableStateOf<AccountStats?>(null)

    val accountStats: State<AccountStats?>
        get() = _accountStats

    // transactions
    private val _recentTransactions = mutableStateOf<List<Transaction>?>(null)

    val recentTransactions: State<List<Transaction>?>
        get() = _recentTransactions

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            val authorizedUserId = getAuthorizedUserIdUsecase.execute()

            getUserProfileUsecase.execute(authorizedUserId).let { userProfile ->
                _userProfile.value = userProfile

                _accountSummary.value = getAccountSummaryUsecase.execute(userProfile.accountId)
                _accountStats.value = getAccountStatsUsecase.execute(userProfile.accountId)
                _recentTransactions.value =
                    getAccountTransactions.execute(userProfile.accountId, limit = MAX_RECENT_TRANSACTIONS)
            }
        }
    }
}
