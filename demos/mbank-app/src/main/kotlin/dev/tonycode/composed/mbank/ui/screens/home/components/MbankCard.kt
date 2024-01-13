package dev.tonycode.composed.mbank.ui.screens.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import dev.tonycode.composed.common.ui.preview.LightDarkPreviews
import dev.tonycode.composed.common.ui.thenIf
import dev.tonycode.composed.mbank.ui.preview.ElementPreview
import dev.tonycode.composed.mbank.ui.theme.MbankTheme


private val defaultPadding = 14.dp

@Composable
fun MbankCard(
    modifier: Modifier = Modifier,
    backgroundColor: Color = MbankTheme.colorScheme.surface,
    padding: Dp = defaultPadding,
    onClicked: (() -> Unit)? = null,
    content: @Composable (() -> Unit),
) = MbankCard(modifier, backgroundColor, padding, padding, onClicked, content)

@Composable
fun MbankCard(
    modifier: Modifier = Modifier,
    backgroundColor: Color = MbankTheme.colorScheme.surface,
    horizontalPadding: Dp = defaultPadding,
    verticalPadding: Dp = defaultPadding,
    onClicked: (() -> Unit)? = null,
    content: @Composable (() -> Unit),
) {

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(color = backgroundColor)
            .thenIf(onClicked != null) {
                clickable { onClicked?.invoke() }
            }
            .padding(horizontalPadding, verticalPadding),
        contentAlignment = Alignment.CenterStart,
    ) {
        content()
    }

}


@LightDarkPreviews
@Composable
fun PreviewMbankCard() = ElementPreview {
    MbankCard {
        Text(
            "Card content",
            style = MbankTheme.typography.bodyEmphasis,
            color = MbankTheme.colorScheme.onSurface,
        )
    }
}
