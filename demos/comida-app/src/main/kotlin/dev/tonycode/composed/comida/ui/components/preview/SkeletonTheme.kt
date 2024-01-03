package dev.tonycode.composed.comida.ui.components.preview

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import dev.tonycode.composed.comida.ui.theme.ComidaPalette


data class SkeletonTheme(

    val shape: Shape,

    val color: Color

)


val defaultSkeletonTheme = SkeletonTheme(
    shape = RoundedCornerShape(33),
    color = ComidaPalette.KinglyCloud,
)

val LocalSkeletonTheme = staticCompositionLocalOf { defaultSkeletonTheme }
