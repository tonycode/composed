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
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import dev.tonycode.composed.common.designsystem.presentation.card.CardJoint
import dev.tonycode.composed.common.designsystem.ui.modifier.thenIf
import dev.tonycode.composed.common.designsystem.ui.preview.LightDarkPreviews
import dev.tonycode.composed.mbank.ui.preview.ElementPreview
import dev.tonycode.composed.mbank.ui.theme.MbankTheme

private val defaultCornerRadius = 16.dp
private val defaultPadding = 14.dp

@Composable
fun MbankCard(
    modifier: Modifier = Modifier,
    cardJoint: CardJoint = CardJoint.Single,
    backgroundColor: Color = MbankTheme.colorScheme.surface,
    padding: Dp = defaultPadding,
    onClicked: (() -> Unit)? = null,
    content: @Composable (() -> Unit),
) = MbankCard(modifier, cardJoint, backgroundColor, padding, padding, onClicked, content)

@Composable
fun MbankCard(
    modifier: Modifier = Modifier,
    cardJoint: CardJoint = CardJoint.Single,
    backgroundColor: Color = MbankTheme.colorScheme.surface,
    horizontalPadding: Dp = defaultPadding,
    verticalPadding: Dp = defaultPadding,
    onClicked: (() -> Unit)? = null,
    content: @Composable (() -> Unit),
) {
    Box(
        modifier =
            modifier
                .clip(
                    when (cardJoint) {
                        CardJoint.Single ->
                            RoundedCornerShape(defaultCornerRadius)

                        CardJoint.Top ->
                            RoundedCornerShape(topStart = defaultCornerRadius, topEnd = defaultCornerRadius)
                        CardJoint.Middle ->
                            RectangleShape
                        CardJoint.Bottom ->
                            RoundedCornerShape(bottomStart = defaultCornerRadius, bottomEnd = defaultCornerRadius)
                    },
                ).background(color = backgroundColor)
                .thenIf(onClicked != null) {
                    clickable { onClicked?.invoke() }
                }.padding(horizontalPadding, verticalPadding),
        contentAlignment = Alignment.CenterStart,
    ) {
        content()
    }
}

@LightDarkPreviews
@Composable
private fun MbankCardPreview(
    @PreviewParameter(CardJointPreviewParameterProvider::class) cardJoint: CardJoint,
) = ElementPreview {
    MbankCard(cardJoint = cardJoint) {
        Text(
            "Card content (cardJoint = ${ cardJoint.name })",
            style = MbankTheme.typography.bodyEmphasis,
            color = MbankTheme.colorScheme.onSurface,
        )
    }
}

private class CardJointPreviewParameterProvider : PreviewParameterProvider<CardJoint> {
    override val values = CardJoint.entries.asSequence()
}
