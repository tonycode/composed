package dev.tonycode.composed.mbank.ui.theme

import android.app.Activity
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.ViewCompat

class MbankColorScheme(
    val background: Color,
    val onBackground: Color,
    val bottomNavigation: Color,
    val bottomNavigationStroke: Color,
    val onBottomNavigation: Color,
    val onBottomNavigationAccent: Color,
    val surface: Color,
    val onSurface: Color,
    val onSurfaceAccent: Color,
    val onSurfaceSecondary: Color,
    val card: Color,
    val onCard: Color,
    val onCardSecondary: Color,
    val primary: Color,
    val accent: Color,
)

private val LightColorScheme =
    MbankColorScheme(
        background = Color(0xFFF3EFF4),
        onBackground = MbankPalette.Black,
        bottomNavigation = Color(0xFFF9F9F9),
        bottomNavigationStroke = Color(0xFF3C3C43).copy(alpha = 0.36f),
        onBottomNavigation = MbankPalette.GreyWeb,
        onBottomNavigationAccent = MbankPalette.Cloisonne,
        surface = Color(0xFFFFFBFF),
        onSurface = MbankPalette.Black,
        onSurfaceAccent = Color(0xFFAD1710),
        onSurfaceSecondary = MbankPalette.GreyWeb,
        card = Color(0xFFF3EFF4),
        onCard = MbankPalette.Black,
        onCardSecondary = MbankPalette.GreyWeb,
        primary = MbankPalette.Cloisonne,
        accent = MbankPalette.AtomicOrange,
    )

private val DarkColorScheme =
    MbankColorScheme(
        background = MbankPalette.Black,
        onBackground = MbankPalette.White,
        bottomNavigation = Color(0xFF151515),
        bottomNavigationStroke = Color(0xFF3C3C43).copy(alpha = 0.36f),
        onBottomNavigation = MbankPalette.Grey,
        onBottomNavigationAccent = Color(0xFF186FC5),
        surface = Color(0xFF1C1C1E),
        onSurface = MbankPalette.White,
        onSurfaceAccent = Color(0xFFC21B12),
        onSurfaceSecondary = MbankPalette.Grey,
        card = Color(0xFF282828),
        onCard = MbankPalette.White,
        onCardSecondary = MbankPalette.Grey,
        primary = MbankPalette.Cloisonne,
        accent = MbankPalette.AtomicOrange,
    )

@Composable
fun MbankAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable (() -> Unit),
) {
    val colorScheme: MbankColorScheme =
        when {
            darkTheme -> DarkColorScheme
            else -> LightColorScheme
        }

    // setup status-bar colors
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            (view.context as Activity).window.statusBarColor = colorScheme.background.toArgb()
            @Suppress("DEPRECATION")
            ViewCompat.getWindowInsetsController(view)?.isAppearanceLightStatusBars = !darkTheme
        }
    }

    val rippleIndication = rememberRipple()

    CompositionLocalProvider(
        LocalColorScheme provides colorScheme,
        LocalTypography provides MbankTypography(),
        LocalIndication provides rippleIndication,
        content = content,
    )
}

private val LocalColorScheme = staticCompositionLocalOf { LightColorScheme }

object MbankTheme {
    val colorScheme: MbankColorScheme
        @Composable
        @ReadOnlyComposable
        get() = LocalColorScheme.current

    val typography: MbankTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current
}
