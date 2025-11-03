package dev.tonycode.composed.mbank.presentation.home

import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import com.arkivanov.essenty.lifecycle.Lifecycle
import com.arkivanov.mvikotlin.core.binder.BinderLifecycleMode
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.bind
import com.arkivanov.mvikotlin.extensions.coroutines.events
import com.arkivanov.mvikotlin.extensions.coroutines.states
import dev.tonycode.composed.mbank.domain.usecase.account.GetAccountStatsUsecase
import dev.tonycode.composed.mbank.domain.usecase.account.GetAccountSummaryUsecase
import dev.tonycode.composed.mbank.domain.usecase.account.GetAccountTransactionsUsecase
import dev.tonycode.composed.mbank.domain.usecase.user.GetAuthorizedUserIdUsecase
import dev.tonycode.composed.mbank.domain.usecase.user.GetUserProfileUsecase
import dev.tonycode.composed.mbank.presentation.IDispatchers
import dev.tonycode.composed.mbank.presentation.home.store.homeStore

class HomeController(
    getAuthorizedUserIdUsecase: GetAuthorizedUserIdUsecase,
    getUserProfileUsecase: GetUserProfileUsecase,
    getAccountSummaryUsecase: GetAccountSummaryUsecase,
    getAccountStatsUsecase: GetAccountStatsUsecase,
    getAccountTransactions: GetAccountTransactionsUsecase,
    private val storeFactory: StoreFactory,
    instanceKeeper: InstanceKeeper,
    private val dispatchers: IDispatchers,
) {
    private val homeStore =
        instanceKeeper.getStore {
            storeFactory.homeStore(
                getAuthorizedUserIdUsecase = getAuthorizedUserIdUsecase,
                getUserProfileUsecase = getUserProfileUsecase,
                getAccountSummaryUsecase = getAccountSummaryUsecase,
                getAccountStatsUsecase = getAccountStatsUsecase,
                getAccountTransactions = getAccountTransactions,
                mainContext = dispatchers.main,
                ioContext = dispatchers.io,
            )
        }

    fun onViewCreated(
        view: HomeView,
        viewLifecycle: Lifecycle,
    ) {
        bind(viewLifecycle, BinderLifecycleMode.CREATE_DESTROY, dispatchers.unconfined) {
            view.events bindTo homeStore
        }

        bind(viewLifecycle, BinderLifecycleMode.START_STOP, dispatchers.unconfined) {
            homeStore.states bindTo view
        }
    }
}
