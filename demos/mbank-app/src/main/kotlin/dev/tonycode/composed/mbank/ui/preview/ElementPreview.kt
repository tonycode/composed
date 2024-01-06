package dev.tonycode.composed.mbank.ui.preview

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.tonycode.composed.common.ui.thenIf
import dev.tonycode.composed.mbank.ui.theme.MbankAppTheme
import dev.tonycode.composed.mbank.ui.theme.MbankTheme


private val paddingSize = 8.dp

@Composable
fun ElementPreview(
    showBackground: Boolean = true,
    maxWidth: Boolean = true,
    usePadding: Boolean = true,
    content: @Composable (() -> Unit),
) {

    MbankAppTheme {
        when {
            showBackground -> {
                Surface(
                    color = MbankTheme.colorScheme.background,
                    modifier = Modifier
                        .thenIf(maxWidth) { fillMaxWidth() }
                        .thenIf(usePadding) { padding(paddingSize) },
                    content = content
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
