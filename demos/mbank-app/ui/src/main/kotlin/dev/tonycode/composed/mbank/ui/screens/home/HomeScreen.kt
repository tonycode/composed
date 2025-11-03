package dev.tonycode.composed.mbank.ui.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import com.arkivanov.essenty.lifecycle.Lifecycle
import com.arkivanov.mvikotlin.core.store.StoreFactory
import dev.tonycode.composed.mbank.data.usecase.account.GetAccountStatsUsecaseImpl
import dev.tonycode.composed.mbank.data.usecase.account.GetAccountSummaryUsecaseImpl
import dev.tonycode.composed.mbank.data.usecase.account.GetAccountTransactionsUsecaseImpl
import dev.tonycode.composed.mbank.data.usecase.user.GetAuthorizedUserIdUsecaseImpl
import dev.tonycode.composed.mbank.data.usecase.user.GetUserProfileUsecaseImpl
import dev.tonycode.composed.mbank.presentation.IDispatchers
import dev.tonycode.composed.mbank.presentation.ViewProxy
import dev.tonycode.composed.mbank.presentation.home.HomeController
import dev.tonycode.composed.mbank.presentation.home.HomeView
import dev.tonycode.composed.mbank.presentation.home.store.HomeStore
import dev.tonycode.composed.mbank.ui.screens.home.components.HomeAppbar
import dev.tonycode.composed.mbank.ui.screens.home.widgets.BalanceOverview
import dev.tonycode.composed.mbank.ui.screens.home.widgets.RecentTransactionsWidget
import dev.tonycode.composed.mbank.ui.screens.home.widgets.SpendingStatsWidget

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    storeFactory: StoreFactory,
    lifecycle: Lifecycle,
    instanceKeeper: InstanceKeeper,
    dispatches: IDispatchers,
) {
    val controller: HomeController by remember {
        mutableStateOf(
            HomeController(
                GetAuthorizedUserIdUsecaseImpl(),
                GetUserProfileUsecaseImpl(),
                GetAccountSummaryUsecaseImpl(),
                GetAccountStatsUsecaseImpl(),
                GetAccountTransactionsUsecaseImpl(),
                storeFactory,
                instanceKeeper,
                dispatches,
            ),
        )
    }

    var model: HomeStore.State by remember { mutableStateOf(HomeStore.State()) }

    val view by remember {
        mutableStateOf(
            object :
                ViewProxy<HomeStore.State, HomeStore.Intent>(
                    render = { model = it },
                ),
                HomeView {},
        )
    }

    LaunchedEffect(true) {
        controller.onViewCreated(view, lifecycle)
    }

    Surface(
        modifier = modifier.fillMaxWidth(),
        color = Color.Unspecified,
    ) {
        Column {
            // top app-bar
            model.userProfile?.name.let {
                HomeAppbar(
                    isLoading = it.isNullOrBlank(),
                    userName = it,
                )
            }

            Column(
                modifier =
                    Modifier
                        .verticalScroll(rememberScrollState())
                        .padding(start = 8.dp, top = 8.dp, end = 8.dp, bottom = 24.dp),
            ) {
                // available funds & spending stats
                Row {
                    BalanceOverview(
                        availableFunds = model.accountSummary?.fundsAvailable,
                        spentThisMonth = model.accountStats?.spentThisMonth,
                        modifier =
                            Modifier
                                .weight(2 / 3f)
                                .height(163.dp),
                    )

                    Spacer(Modifier.width(8.dp))

                    SpendingStatsWidget(Modifier.height(163.dp))
                }

                Spacer(Modifier.height(8.dp))

                RecentTransactionsWidget(
                    transactions = model.recentTransactions,
                )
            }
        }
    }
}
