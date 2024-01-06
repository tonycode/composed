package dev.tonycode.composed.mbank.ui.preview

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import dev.tonycode.composed.mbank.ui.theme.MbankAppTheme
import dev.tonycode.composed.mbank.ui.theme.MbankTheme


@Composable
fun ScreenPreview(
    content: @Composable (() -> Unit),
) {

    MbankAppTheme {
        Surface(
            color = MbankTheme.colorScheme.background,
            content = content
        )
    }

}
