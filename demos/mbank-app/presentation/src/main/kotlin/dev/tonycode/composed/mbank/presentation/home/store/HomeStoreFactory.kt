package dev.tonycode.composed.mbank.presentation.home.store

import com.arkivanov.mvikotlin.core.store.SimpleBootstrapper
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.core.utils.JvmSerializable
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
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
import kotlinx.coroutines.supervisorScope
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

internal fun StoreFactory.homeStore(
    getAuthorizedUserIdUsecase: GetAuthorizedUserIdUsecase,
    getUserProfileUsecase: GetUserProfileUsecase,
    getAccountSummaryUsecase: GetAccountSummaryUsecase,
    getAccountStatsUsecase: GetAccountStatsUsecase,
    getAccountTransactions: GetAccountTransactionsUsecase,
    mainContext: CoroutineContext,
    ioContext: CoroutineContext,
): HomeStore =
    object :
        HomeStore,
        Store<HomeStore.Intent, HomeStore.State, Nothing> by create(
            name = "HomeStore",
            initialState = HomeStore.State(),
            bootstrapper = SimpleBootstrapper(Action.Init),
            executorFactory = {
                ExecutorImpl(
                    getAuthorizedUserIdUsecase = getAuthorizedUserIdUsecase,
                    getUserProfileUsecase = getUserProfileUsecase,
                    getAccountSummaryUsecase = getAccountSummaryUsecase,
                    getAccountStatsUsecase = getAccountStatsUsecase,
                    getAccountTransactions = getAccountTransactions,
                    mainContext = mainContext,
                    ioContext = ioContext,
                )
            },
            reducer = { reduce(it) },
        ) {}

private sealed interface Action {
    data object Init : Action
}

private sealed interface Msg : JvmSerializable {
    data class UserProfileLoaded(
        val userProfile: UserProfile,
    ) : Msg

    data class AccountSummaryLoaded(
        val accountSummary: AccountSummary,
    ) : Msg

    data class AccountStatsLoaded(
        val accountStats: AccountStats,
    ) : Msg

    data class RecentTransactionsLoaded(
        val recentTransactions: List<Transaction>,
    ) : Msg
}

private class ExecutorImpl(
    private val getAuthorizedUserIdUsecase: GetAuthorizedUserIdUsecase,
    private val getUserProfileUsecase: GetUserProfileUsecase,
    private val getAccountSummaryUsecase: GetAccountSummaryUsecase,
    private val getAccountStatsUsecase: GetAccountStatsUsecase,
    private val getAccountTransactions: GetAccountTransactionsUsecase,
    mainContext: CoroutineContext,
    private val ioContext: CoroutineContext,
) : CoroutineExecutor<HomeStore.Intent, Action, HomeStore.State, Msg, Nothing>(mainContext) {
    override fun executeAction(action: Action) {
        when (action) {
            is Action.Init -> refresh()
        }
    }

    override fun executeIntent(intent: HomeStore.Intent) {
        when (intent) {
            is HomeStore.Intent.Refresh -> refresh()
        }
    }

    private fun refresh() {
        scope.launch {
            val authorizedUserId =
                withContext(ioContext) {
                    getAuthorizedUserIdUsecase.execute()
                }

            val userProfile =
                withContext(ioContext) {
                    getUserProfileUsecase.execute(authorizedUserId)
                }
            dispatch(Msg.UserProfileLoaded(userProfile))

            val accountId = userProfile.accountId

            supervisorScope {
                launch {
                    val accountSummary =
                        withContext(ioContext) {
                            getAccountSummaryUsecase.execute(accountId)
                        }
                    dispatch(Msg.AccountSummaryLoaded(accountSummary))
                }

                launch {
                    val accountStats =
                        withContext(ioContext) {
                            getAccountStatsUsecase.execute(accountId)
                        }
                    dispatch(Msg.AccountStatsLoaded(accountStats))
                }

                launch {
                    val recentTransactions =
                        withContext(ioContext) {
                            getAccountTransactions.execute(accountId, limit = 10)
                        }
                    dispatch(Msg.RecentTransactionsLoaded(recentTransactions))
                }
            }
        }
    }
}

private fun HomeStore.State.reduce(msg: Msg): HomeStore.State =
    when (msg) {
        is Msg.UserProfileLoaded -> copy(userProfile = msg.userProfile)
        is Msg.AccountSummaryLoaded -> copy(accountSummary = msg.accountSummary)
        is Msg.AccountStatsLoaded -> copy(accountStats = msg.accountStats)
        is Msg.RecentTransactionsLoaded -> copy(recentTransactions = msg.recentTransactions)
    }
