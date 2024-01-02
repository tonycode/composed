package dev.tonycode.composed.comida.ui.screens.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.tonycode.composed.comida.R
import dev.tonycode.composed.comida.ui.components.ImageButton
import dev.tonycode.composed.comida.ui.screenHorizontalPadding
import dev.tonycode.composed.comida.ui.theme.ComidaAppTheme
import dev.tonycode.composed.comida.ui.theme.ComidaPalette


@Composable
fun ComidaTopAppbar(
    modifier: Modifier = Modifier,
    onNavMenuClicked: () -> Unit,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        ImageButton(onClick = { onNavMenuClicked.invoke() }) {
            Image(
                painterResource(R.drawable.ic_nav_menu_40),
                contentDescription = "open nav menu",
            )
        }

        DeliveryInfoView(
            street = "387 Merdina",
            onSelectorClicked = { },
        )

        Surface(
            shape = MaterialTheme.shapes.small,
            color = ComidaPalette.MistyBlue.copy(alpha = 0.15f),
            modifier = Modifier.size(40.dp),
        ) {
            // Avatar
        }
    }
}

@Preview
@Composable
private fun PreviewComidaTopAppbar() {
    ComidaAppTheme {
        Surface(
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier.fillMaxWidth(),
        ) {
            ComidaTopAppbar(
                modifier = Modifier.padding(horizontal = screenHorizontalPadding),
                onNavMenuClicked = { },
            )
        }
    }
}
