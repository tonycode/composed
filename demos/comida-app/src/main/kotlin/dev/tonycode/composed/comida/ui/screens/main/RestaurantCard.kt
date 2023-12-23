package dev.tonycode.composed.comida.ui.screens.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.tonycode.composed.comida.ui.theme.ComidaAppTheme
import dev.tonycode.composed.comida.ui.theme.ComidaPalette


@Composable
fun RestaurantCard(
    title: String,
) {

    Surface(
        shape = MaterialTheme.shapes.medium,
        color = ComidaPalette.MistyBlue,
        modifier = Modifier.size(width = 264.dp, height = 176.dp),
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                title,
                style = MaterialTheme.typography.headlineMedium,
                color = ComidaPalette.White,
            )
        }
    }

}

@Preview
@Composable
private fun PreviewRestaurantCard() {
    ComidaAppTheme {
        Surface(
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier.fillMaxWidth(),
        ) {
            RestaurantCard("Seafood maki sushi")
        }
    }
}
