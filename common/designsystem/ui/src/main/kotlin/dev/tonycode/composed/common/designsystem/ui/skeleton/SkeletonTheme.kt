package dev.tonycode.composed.common.designsystem.ui.skeleton

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape


data class SkeletonTheme(

    val shape: Shape,

    val color: Color

)


val defaultSkeletonTheme = SkeletonTheme(
    shape = RoundedCornerShape(33),
    color = Color.LightGray,
)

val LocalSkeletonTheme = staticCompositionLocalOf { defaultSkeletonTheme }
