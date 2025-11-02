package dev.tonycode.composed.mbank.ui.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dev.tonycode.composed.common.designsystem.ui.preview.LightDarkPreviews
import dev.tonycode.composed.mbank.ui.Screen
import dev.tonycode.composed.mbank.ui.preview.ElementPreview
import dev.tonycode.composed.mbank.ui.screens
import dev.tonycode.composed.mbank.ui.theme.MbankTheme

@Composable
fun MbankBottomBar(
    items: List<Screen>,
    selectedItem: Screen,
    onItemSelected: (item: Screen) -> Unit,
) {
    Surface(
        shape = RectangleShape,
        color = MbankTheme.colorScheme.bottomNavigation,
    ) {
        Column {
            Box(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(color = MbankTheme.colorScheme.bottomNavigationStroke),
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
            ) {
                items.forEach {
                    NavItem(
                        titleRes = it.titleRes,
                        iconRes = it.iconRes,
                        isSelected = (it == selectedItem),
                        onSelected = {
                            onItemSelected.invoke(it)
                        },
                    )
                }
            }
        }
    }
}

@Composable
private fun NavItem(
    @StringRes titleRes: Int,
    @DrawableRes iconRes: Int,
    isSelected: Boolean = false,
    onSelected: () -> Unit,
) {
    Column(
        modifier =
            Modifier
                .clip(RoundedCornerShape(26.dp))
                .clickable { onSelected.invoke() }
                .padding(vertical = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Crossfade(
            targetState = isSelected,
            label = "bottom-nav $titleRes crossfade",
        ) { isSelected ->
            Box(
                modifier =
                    Modifier
                        .size(width = 64.dp, height = 32.dp)
                        .background(
                            color =
                                if (isSelected) {
                                    MbankTheme.colorScheme.onBottomNavigationAccent
                                } else {
                                    Color.Unspecified
                                },
                            shape = RoundedCornerShape(20.dp),
                        ),
                contentAlignment = Alignment.Center,
            ) {
                Image(
                    painterResource(iconRes),
                    contentDescription = null,
                    colorFilter =
                        ColorFilter.tint(
                            color =
                                if (!isSelected) {
                                    MbankTheme.colorScheme.onBottomNavigation
                                } else {
                                    MbankTheme.colorScheme.bottomNavigation
                                },
                        ),
                    modifier = Modifier.size(24.dp),
                )
            }
        }

        Spacer(Modifier.height(4.dp))

        Text(
            stringResource(titleRes),
            style = MbankTheme.typography.label,
            color =
                if (!isSelected) {
                    MbankTheme.colorScheme.onBottomNavigation
                } else {
                    MbankTheme.colorScheme.onBottomNavigationAccent
                },
        )
    }
}

@LightDarkPreviews
@Composable
private fun MbankBottomBarPreview() =
    ElementPreview(usePadding = false) {
        var selectedItem: Screen by remember { mutableStateOf(Screen.Home) }

        MbankBottomBar(
            items = screens,
            selectedItem = selectedItem,
            onItemSelected = {
                selectedItem = it
            },
        )
    }
