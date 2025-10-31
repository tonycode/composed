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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.tonycode.composed.common.designsystem.ui.preview.LightDarkPreviews
import dev.tonycode.composed.mbank.model.AccountStats
import dev.tonycode.composed.mbank.model.AccountSummary
import dev.tonycode.composed.mbank.model.Transaction
import dev.tonycode.composed.mbank.model.UserProfile
import dev.tonycode.composed.mbank.ui.preview.ScreenPreview
import dev.tonycode.composed.mbank.ui.screens.home.components.HomeAppbar
import dev.tonycode.composed.mbank.ui.screens.home.widgets.BalanceOverview
import dev.tonycode.composed.mbank.ui.screens.home.widgets.RecentTransactionsWidget
import dev.tonycode.composed.mbank.ui.screens.home.widgets.SpendingStatsWidget

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel = viewModel(),
) {
    val userProfile: UserProfile? by remember { homeViewModel.userProfile }
    val accountSummary: AccountSummary? by remember { homeViewModel.accountSummary }
    val accountStats: AccountStats? by remember { homeViewModel.accountStats }
    val recentTransactions: List<Transaction>? by remember { homeViewModel.recentTransactions }

    Surface(
        modifier = modifier.fillMaxWidth(),
        color = Color.Unspecified,
    ) {
        Column {
            // top app-bar
            userProfile.let {
                HomeAppbar(
                    isLoading = (it == null),
                    userName = it?.name,
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
                        availableFunds = accountSummary?.fundsAvailable,
                        spentThisMonth = accountStats?.spentThisMonth,
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
                    transactions = recentTransactions,
                )
            }
        }
    }
}

@LightDarkPreviews
@Composable
private fun HomeScreenPreview() =
    ScreenPreview {
        HomeScreen()
    }
