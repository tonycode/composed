package dev.tonycode.composed.mbank.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import dev.tonycode.composed.common.ui.nunitoSansFamily


class MbankTypography(

    val appbarTitle: TextStyle = DefaultTextStyle.copy(
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        lineHeight = 28.sp,
    ),

    val bodyEmphasis: TextStyle = DefaultTextStyle.copy(
        fontWeight = FontWeight.Bold,
    ),

    val body: TextStyle = DefaultTextStyle,

    val label: TextStyle = DefaultTextStyle.copy(
        fontSize = 12.sp,
        lineHeight = 13.sp,
        letterSpacing = 0.5.sp,
    )

)

private val DefaultTextStyle = TextStyle.Default.copy(
    fontFamily = nunitoSansFamily,
    fontWeight = FontWeight.Normal,
    fontSize = 14.sp,
    lineHeight = 16.sp,
    letterSpacing = 0.sp,
    platformStyle = PlatformTextStyle(includeFontPadding = false),
)


internal val LocalTypography = staticCompositionLocalOf { MbankTypography() }
