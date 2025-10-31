package dev.tonycode.composed.common.designsystem.ui

import androidx.compose.ui.text.font.DeviceFontFamilyName
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight


val robotoFamily =
    FontFamily(Font(DeviceFontFamilyName("sans-serif")))

val robotoCondensedFamily =
    FontFamily(Font(DeviceFontFamilyName("sans-serif-condensed")))

val notoSerifFamily =
    FontFamily(Font(DeviceFontFamilyName("serif")))

val droidSansMonoFamily =
    FontFamily(Font(DeviceFontFamilyName("monospace")))

val cutiveMonoFamily =
    FontFamily(Font(DeviceFontFamilyName("serif-monospace")))

val dmSansFamily = FontFamily(
    Font(R.font.dm_sans_regular, FontWeight.Normal),
    Font(R.font.dm_sans_medium, FontWeight.Medium),
    Font(R.font.dm_sans_bold, FontWeight.Bold),
)

val nunitoSansFamily = FontFamily(
    Font(R.font.nunito_sans_light, FontWeight.Light),
    Font(R.font.nunito_sans_regular, FontWeight.Normal),
    Font(R.font.nunito_sans_medium, FontWeight.Medium),
    Font(R.font.nunito_sans_semi_bold, FontWeight.SemiBold),
    Font(R.font.nunito_sans_bold, FontWeight.Bold),
)

val poppinsFamily = FontFamily(
    Font(R.font.poppins_regular, FontWeight.Normal),
    Font(R.font.poppins_medium, FontWeight.Medium),
    Font(R.font.poppins_semi_bold, FontWeight.SemiBold),
    Font(R.font.poppins_bold, FontWeight.Bold),
)
