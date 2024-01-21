package dev.tonycode.composed.comida.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.ViewCompat
import dev.tonycode.composed.common.ui.skeleton.LocalSkeletonTheme
import dev.tonycode.composed.common.ui.skeleton.defaultSkeletonTheme


//private val DarkColorScheme = darkColorScheme(
//    background = ComidaPalette.Dark,
//    onBackground = ComidaPalette.White,
//    surface = ComidaPalette.PianoBlack,
//    onSurface = ComidaPalette.GhostWhite,
//    secondaryContainer = ComidaPalette.Tamahagane,
//    onSecondaryContainer = ComidaPalette.GhostWhite,
//
//    primary = ComidaPalette.SilverPhoenix,
//    onPrimary = ComidaPalette.Walrus,
//    secondary = ComidaPalette.GhostWhite,
//    onSecondary = ComidaPalette.GhostWhite,
//    tertiary = ComidaPalette.Walrus,
//    onTertiary = ComidaPalette.Tamahagane,
//)

private val LightColorScheme = lightColorScheme(
    background = ComidaPalette.White,
    onBackground = ComidaPalette.PrimaryText,
    surface = ComidaPalette.PrimaryText,
    onSurface = ComidaPalette.White,
    secondaryContainer = ComidaPalette.MistyBlue,
    onSecondaryContainer = ComidaPalette.Black,
    surfaceVariant = ComidaPalette.Doctor,

    primary = ComidaPalette.Primary,
    onPrimary = ComidaPalette.White,
    secondary = ComidaPalette.ParisPaving,
    onSecondary = ComidaPalette.White,
//    tertiary = ComidaPalette.PianoBlack,
//    onTertiary = ComidaPalette.DiscoBall,
)


@Composable
fun ComidaAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+ (API 31+)
    dynamicColor: Boolean = false,
    content: @Composable (() -> Unit)
) {
    val colorScheme = when {
//        dynamicColor && (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) -> {
//            val context = LocalContext.current
//            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
//        }
//
//        darkTheme -> DarkColorScheme

        else -> LightColorScheme
    }

    // setup status-bar colors
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            (view.context as Activity).window.statusBarColor = ComidaPalette.White.toArgb()
            @Suppress("DEPRECATION")
            ViewCompat.getWindowInsetsController(view)?.isAppearanceLightStatusBars = true
        }
    }

    CompositionLocalProvider(
        LocalSkeletonTheme provides defaultSkeletonTheme.copy(color = ComidaPalette.KinglyCloud),
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = comidaTypography,
            shapes = comidaShapes,
            content = content
        )
    }
}
