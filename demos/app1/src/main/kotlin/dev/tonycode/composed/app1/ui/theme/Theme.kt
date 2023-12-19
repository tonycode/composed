package dev.tonycode.composed.app1.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.ViewCompat


private val DarkColorScheme = darkColorScheme(
    background = Palette.PianoBlack,
    onBackground = Palette.GhostWhite,
    surface = Palette.PianoBlack,
    onSurface = Palette.GhostWhite,
    secondaryContainer = Palette.Tamahagane,
    onSecondaryContainer = Palette.GhostWhite,

    primary = Palette.SilverPhoenix,
    onPrimary = Palette.Walrus,
    secondary = Palette.GhostWhite,
    onSecondary = Palette.GhostWhite,
    tertiary = Palette.Walrus,
    onTertiary = Palette.Tamahagane,
)

private val LightColorScheme = lightColorScheme(
    background = Palette.GhostWhite,
    onBackground = Palette.PianoBlack,
    surface = Color(0xffeaecfa),
    onSurface = Palette.PianoBlack,
    secondaryContainer = Color(0xffc1c5f1),
    onSecondaryContainer = Palette.PianoBlack,

    primary = Palette.DepthOfNight,
    onPrimary = Palette.SilverPhoenix,
    secondary = Palette.Walrus,
    onSecondary = Palette.GhostWhite,
    tertiary = Palette.PianoBlack,
    onTertiary = Palette.DiscoBall,
)


@Suppress("DEPRECATION")
@Composable
fun App1Theme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme

        else -> LightColorScheme
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            (view.context as Activity).window.statusBarColor = colorScheme.primary.toArgb()
            ViewCompat.getWindowInsetsController(view)?.isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography(),
        shapes = Shapes,
        content = content
    )
}
