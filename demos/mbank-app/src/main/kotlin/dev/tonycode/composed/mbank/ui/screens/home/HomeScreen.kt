package dev.tonycode.composed.mbank.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.tonycode.composed.common.ui.preview.LightDarkPreviews
import dev.tonycode.composed.mbank.R
import dev.tonycode.composed.mbank.model.UserProfile
import dev.tonycode.composed.mbank.ui.preview.ScreenPreview
import dev.tonycode.composed.mbank.ui.screens.home.components.HomeAppbar
import dev.tonycode.composed.mbank.ui.screens.home.components.MbankCard
import dev.tonycode.composed.mbank.ui.theme.MbankTheme


@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = viewModel(),
) {

    val userProfile: UserProfile? by remember { homeViewModel.userProfile }

    Surface(
        modifier = Modifier.fillMaxWidth(),
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
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(start = 8.dp, top = 8.dp, end = 8.dp, bottom = 24.dp),
            ) {

                Row {
                    MbankCard(
                        modifier = Modifier
                            .weight(2 / 3f)
                            .height(163.dp),
                    ) {
                        Column() {
                            Text(
                                "Available funds",
                                style = MbankTheme.typography.body,
                                color = MbankTheme.colorScheme.onSurface,
                            )

                            Spacer(Modifier.width(8.dp))

                            Text(
                                "123.45",
                                style = MbankTheme.typography.bodyEmphasis,
                                color = MbankTheme.colorScheme.onSurface,
                            )
                        }
                    }

                    Spacer(Modifier.width(8.dp))

                    MbankCard(modifier = Modifier.height(163.dp)) {
                        Text(
                            "Mon Tue Wed Thu Fri",
                            style = MbankTheme.typography.label,
                            color = MbankTheme.colorScheme.onSurface,
                        )
                    }
                }

                Spacer(Modifier.height(8.dp))

                MbankCard {
                    Column {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                        ) {
                            Text(
                                stringResource(R.string.recent_operations),
                                style = MbankTheme.typography.bodyEmphasis,
                                color = MbankTheme.colorScheme.onSurface,
                            )

                            Text(
                                stringResource(R.string.full_history),
                                style = MbankTheme.typography.bodyEmphasis,
                                color = MbankTheme.colorScheme.onSurfaceAccent,
                            )
                        }
                    }
                }
            }
        }
    }

}

@LightDarkPreviews
@Composable
fun PreviewHomeScreen() = ScreenPreview {
    HomeScreen()
}
