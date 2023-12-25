package dev.tonycode.composed.comida.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.tonycode.composed.comida.R
import dev.tonycode.composed.comida.ui.theme.ComidaAppTheme
import dev.tonycode.composed.comida.ui.theme.ComidaPalette


@Composable
fun BottomNav(
    modifier: Modifier = Modifier,
) {

    var selectedRoute by remember { mutableStateOf(navItems.first().route) }

    Surface(
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.surface,
        modifier = modifier,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            navItems.forEach { screen ->
                BottomNavItem(
                    screen,
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
    isSelected: Boolean,
    onSelected: () -> Unit,
) {
    Image(
        painterResource(id = screen.iconRes),
        contentDescription = screen.iconDescription,
        colorFilter = ColorFilter.tint(
            if (isSelected) ComidaPalette.Primary else ComidaPalette.ParisPaving
        ),
        modifier = Modifier.clickable { onSelected.invoke() }
    )
}

private sealed class Screen(val route: String, @DrawableRes val iconRes: Int, val iconDescription: String) {
    object Home : Screen("home", R.drawable.nav_home_28, "Home screen")
    object Cart : Screen("cart", R.drawable.nav_cart_28, "Basket screen")
    object Profile : Screen("profile", R.drawable.nav_profile_28, "Profile screen")
    object Notifications : Screen("notifications", R.drawable.nav_notifications_28, "Notifications screen")
}

private val navItems = listOf(Screen.Home, Screen.Cart, Screen.Profile, Screen.Notifications)


@Preview
@Composable
private fun PreviewBottomNav() {
    ComidaAppTheme {
        Surface(
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier.fillMaxWidth(),
        ) {
            BottomNav(
                modifier = Modifier.padding(24.dp)
            )
        }
    }
}
