package dev.tonycode.composed.ui.preview

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.tonycode.composed.common.designsystem.ui.modifier.thenIf
import dev.tonycode.composed.ui.theme.ComposedAppTheme

private val paddingSize = 8.dp

@Composable
fun ElementPreview(
    showBackground: Boolean = true,
    maxWidth: Boolean = true,
    usePadding: Boolean = true,
    content: @Composable (() -> Unit),
) {
    ComposedAppTheme {
        when {
            showBackground -> {
                Surface(
                    color = MaterialTheme.colorScheme.background,
                    modifier =
                        Modifier
                            .thenIf(maxWidth) { fillMaxWidth() }
                            .thenIf(usePadding) { padding(paddingSize) },
                    content = content,
                )
            }

            usePadding -> {
                Box(modifier = Modifier.padding(paddingSize)) {
                    content()
                }
            }

            else -> {
                content()
            }
        }
    }
}
