package dev.tonycode.composed.comida.ui.preview

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import dev.tonycode.composed.comida.ui.theme.ComidaTheme
import dev.tonycode.composed.common.ui.thenIf


private val paddingSize = 8.dp

@Composable
fun ElementPreview(
    showBackground: Boolean = true,
    backgroundColor: Color = MaterialTheme.colorScheme.background,
    maxWidth: Boolean = true,
    usePadding: Boolean = true,
    content: @Composable (() -> Unit),
) {

    ComidaTheme {
        when {
            showBackground -> {
                Surface(
                    color = backgroundColor,
                    modifier = Modifier
                        .background(color = backgroundColor)  // otherwise padding will be not filled
                        .thenIf(maxWidth) { fillMaxWidth() }
                        .thenIf(usePadding) { padding(paddingSize) },
                    content = content
                )
            }

            usePadding -> {
                Box(
                    modifier = Modifier
                        .background(color = backgroundColor)  // otherwise padding will be not filled
                        .padding(paddingSize),
                ) {
                    content()
                }
            }

            else -> {
                content()
            }
        }
    }

}
