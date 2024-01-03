package dev.tonycode.composed.comida.ui.components.preview

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp


@Composable
fun Skeleton(
    modifier: Modifier = Modifier,
    width: Dp? = null,
    height: Dp? = null,
    shape: Shape = LocalSkeletonTheme.current.shape,
    color: Color = LocalSkeletonTheme.current.color,
) {

    Box(
        modifier = Modifier
            .then(
                if (width != null) Modifier.width(width) else Modifier.fillMaxWidth()
            )
            .then(
                if (height != null) Modifier.height(height) else Modifier.fillMaxHeight()
            )
            .then(modifier)
            .background(color = color, shape = shape),
    )

}
