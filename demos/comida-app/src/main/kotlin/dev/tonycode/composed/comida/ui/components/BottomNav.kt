package dev.tonycode.composed.comida.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.tonycode.composed.comida.R
import dev.tonycode.composed.comida.ui.preview.ElementPreview
import dev.tonycode.composed.comida.ui.theme.ComidaPalette


@Composable
fun BottomNav(
    modifier: Modifier = Modifier,
) {

    var selectedRoute by rememberSaveable { mutableStateOf(navItems.first().route) }

    Surface(
        shape = RoundedCornerShape(20.dp),
        color = MaterialTheme.colorScheme.surface,
        modifier = modifier,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            navItems.forEach { screen ->
                BottomNavItem(
                    screen,
                    badgeCounter = if (screen.route == Screen.Notifications.route) 5 else null,
                    isSelected = (selectedRoute == screen.route),
                    onSelected = { selectedRoute = screen.route },
                )
            }
        }
    }

}

@Composable
private fun BottomNavItem(
    screen: Screen,
    badgeCounter: Int?,
    isSelected: Boolean,
    onSelected: () -> Unit,
) {
    Box(
        modifier = Modifier
            .clip(MaterialTheme.shapes.medium)
            .clickable { onSelected.invoke() }
            .padding(horizontal = 16.dp),
    ) {
        IconWithBadge(
            iconRes = screen.iconRes,
            iconDescription = screen.iconDescription,
            badgeCounter = badgeCounter,
            isSelected = isSelected,
            modifier = Modifier.align(Alignment.Center),
        )

        if (isSelected) {
            SelectedItemIndicator(modifier = Modifier.align(Alignment.BottomCenter))
        }
    }
}

@Composable
private fun IconWithBadge(
    @DrawableRes iconRes: Int,
    iconDescription: String,
    badgeCounter: Int?,
    isSelected: Boolean,
    modifier: Modifier = Modifier,
) {
    val iconSize = 28.dp
    val badgeHorizontalPadding = 2.dp
    val badgeVerticalPadding = 4.dp

    Box(modifier = modifier.padding(vertical = 16.dp - badgeVerticalPadding)) {
        Image(
            painterResource(id = iconRes),
            contentDescription = iconDescription,
            colorFilter = ColorFilter.tint(
                if (isSelected) ComidaPalette.Primary else ComidaPalette.ParisPaving
            ),
            modifier = Modifier
                .align(Alignment.Center)
                .padding(horizontal = badgeHorizontalPadding, vertical = badgeVerticalPadding)
                .requiredSize(
                    width = iconSize + badgeHorizontalPadding*2,
                    height = iconSize + badgeVerticalPadding*2,
                ),
        )

        if (badgeCounter != null) {
            BadgeCounter(
                value = badgeCounter,
                modifier = Modifier.align(Alignment.TopEnd),
            )
        }
    }
}

@Composable
private fun SelectedItemIndicator(
    modifier: Modifier = Modifier,
) {
    Surface(
        shape = MaterialTheme.shapes.small,
        color = MaterialTheme.colorScheme.primary,
        modifier = modifier.size(width = 40.dp, height = 4.dp),
    ) { }
}


private sealed class Screen(val route: String, @DrawableRes val iconRes: Int, val iconDescription: String) {
    object Home : Screen("home", R.drawable.comida_nav_home_28, "Home screen")
    object Cart : Screen("cart", R.drawable.comida_nav_cart_28, "Basket screen")
    object Profile : Screen("profile", R.drawable.comida_nav_profile_28, "Profile screen")
    object Notifications : Screen("notifications", R.drawable.comida_nav_notifications_28, "Notifications screen")
}

private val navItems = listOf(Screen.Home, Screen.Cart, Screen.Profile, Screen.Notifications)


@Preview
@Composable
private fun BottomNavPreview() = ElementPreview {
    BottomNav()
}
