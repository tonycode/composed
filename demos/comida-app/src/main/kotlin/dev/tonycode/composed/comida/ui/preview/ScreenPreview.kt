package dev.tonycode.composed.comida.ui.preview

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import dev.tonycode.composed.comida.ui.theme.ComidaAppTheme


@Composable
fun ScreenPreview(
    content: @Composable (() -> Unit),
) {

    ComidaAppTheme {
        Surface(
            color = MaterialTheme.colorScheme.background,
            content = content
        )
    }

}
