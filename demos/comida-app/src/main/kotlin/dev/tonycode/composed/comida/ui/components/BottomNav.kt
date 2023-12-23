package dev.tonycode.composed.comida.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.tonycode.composed.comida.R
import dev.tonycode.composed.comida.ui.screenHorizontalPadding
import dev.tonycode.composed.comida.ui.theme.ComidaAppTheme
import dev.tonycode.composed.comida.ui.theme.ComidaPalette


@Composable
fun BottomNav(
    modifier: Modifier = Modifier,
) {

    Surface(
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.surface,
        modifier = modifier,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceAround,
        ) {
            Image(
                painterResource(R.drawable.nav_home_28),
                contentDescription = "Home screen",
                colorFilter = ColorFilter.tint(ComidaPalette.Primary),
            )

            Image(
                painterResource(R.drawable.nav_cart_28),
                contentDescription = "Basket screen",
                colorFilter = ColorFilter.tint(ComidaPalette.ParisPaving),
            )

            Image(
                painterResource(R.drawable.nav_profile_28),
                contentDescription = "Profile screen",
                colorFilter = ColorFilter.tint(ComidaPalette.ParisPaving),
            )

            Image(
                painterResource(R.drawable.nav_notifications_28),
                contentDescription = "Notifications screen",
                colorFilter = ColorFilter.tint(ComidaPalette.ParisPaving),
            )
        }
    }

}

@Preview
@Composable
fun PreviewBottomNav() {
    ComidaAppTheme {
        Surface(
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier.fillMaxWidth(),
        ) {
            BottomNav(
                modifier = Modifier.padding(horizontal = screenHorizontalPadding)
            )
        }
    }
}
