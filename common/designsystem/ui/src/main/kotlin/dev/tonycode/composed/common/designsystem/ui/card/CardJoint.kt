package dev.tonycode.composed.common.designsystem.ui.card

import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import dev.tonycode.composed.common.designsystem.presentation.card.CardJoint

fun Modifier.paddingForCardJoint(
    cardJoint: CardJoint,
    verticalPadding: Dp,
): Modifier = when (cardJoint) {
    CardJoint.Single ->
        this.padding(vertical = verticalPadding)

    CardJoint.Top ->
        this.padding(top = verticalPadding)
    CardJoint.Middle ->
        this
    CardJoint.Bottom ->
        this.padding(bottom = verticalPadding)
}
