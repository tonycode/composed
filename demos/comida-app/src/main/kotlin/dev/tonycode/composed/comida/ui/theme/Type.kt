package dev.tonycode.composed.comida.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import dev.tonycode.composed.common.ui.poppinsFamily


val comidaTypography = Typography(

    headlineLarge = TextStyle(
        fontFamily = poppinsFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
    ),

    headlineMedium = TextStyle(
        fontFamily = poppinsFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp,
    ),

    titleSmall = TextStyle(
        fontFamily = poppinsFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
    ),

    bodySmall = TextStyle(
        fontFamily = poppinsFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
    ),

    labelSmall = TextStyle(
        fontFamily = poppinsFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
    )

)
