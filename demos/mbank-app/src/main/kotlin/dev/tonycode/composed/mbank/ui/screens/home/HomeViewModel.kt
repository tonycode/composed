package dev.tonycode.composed.mbank.ui.screens.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.tonycode.composed.mbank.model.AccountStats
import dev.tonycode.composed.mbank.model.AccountSummary
import dev.tonycode.composed.mbank.model.Transaction
import dev.tonycode.composed.mbank.model.UserProfile
import dev.tonycode.composed.mbank.usecase.account.GetAccountStatsUsecase
import dev.tonycode.composed.mbank.usecase.account.GetAccountSummaryUsecase
import dev.tonycode.composed.mbank.usecase.account.GetAccountTransactions
import dev.tonycode.composed.mbank.usecase.user.GetAuthorizedUserIdUsecase
import dev.tonycode.composed.mbank.usecase.user.GetUserProfileUsecase
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val MAX_RECENT_TRANSACTIONS = 4

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {
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
            val authorizedUserId = GetAuthorizedUserIdUsecase().execute()

            GetUserProfileUsecase(authorizedUserId).execute().let { userProfile ->
                _userProfile.value = userProfile

                _accountSummary.value = GetAccountSummaryUsecase(userProfile.accountId).execute()
                _accountStats.value = GetAccountStatsUsecase(userProfile.accountId).execute()
                _recentTransactions.value =
                    GetAccountTransactions(userProfile.accountId, limit = MAX_RECENT_TRANSACTIONS).execute()
            }
        }
    }
}
