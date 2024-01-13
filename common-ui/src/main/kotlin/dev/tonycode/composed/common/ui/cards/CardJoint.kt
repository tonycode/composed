package dev.tonycode.composed.common.ui.cards

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp


enum class CardJoint {

    /** A single, separate card */
    Single,


    /** Top card in a joined list */
    Top,

    /** Any intermediate card in a joined list */
    Middle,

    /** Bottom card in a joined list */
    Bottom

}


@Composable
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
