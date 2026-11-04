package com.example.m3catalog.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

/**
 * =========================================================================================
 * MATERIAL 3 EXPRESSIVE THEME ENGINE
 * Source of Truth: https://m3.material.io/foundations
 *
 * BEGINNER GUIDE: How Jetpack Compose Theming Works:
 * In traditional Android (XML), themes were defined in styles.xml and applied via Android attributes.
 * In Jetpack Compose, the theme is pure Kotlin code.
 *
 * 1. MaterialTheme Composable:
 *    `MaterialTheme(colorScheme = ..., typography = ..., shapes = ...)` acts as a styling provider.
 *    It uses "CompositionLocal" behind the scenes (`LocalColorScheme`, `LocalTypography`, etc.).
 *    This means any child Composable anywhere inside the hierarchy can read `MaterialTheme.colorScheme.primary`
 *    WITHOUT having to pass `colorScheme` as a parameter through dozens of nested functions!
 *
 * 2. Recomposition & Theming:
 *    When you toggle between Light Mode and Dark Mode, Compose does NOT recreate or restart the Activity.
 *    Instead, Compose triggers "Recomposition" — it only re-renders the specific Composables
 *    that read the changed theme values, yielding instantaneous, buttery-smooth 60-120 FPS transitions.
 * =========================================================================================
 */

// -----------------------------------------------------------------------------------------
// LIGHT COLOR SCHEME (M3 Expressive calibrated)
// -----------------------------------------------------------------------------------------
private val LightColorScheme = lightColorScheme(
    primary = PrimaryLight,
    onPrimary = OnPrimaryLight,
    primaryContainer = PrimaryContainerLight,
    onPrimaryContainer = OnPrimaryContainerLight,

    secondary = SecondaryLight,
    onSecondary = OnSecondaryLight,
    secondaryContainer = SecondaryContainerLight,
    onSecondaryContainer = OnSecondaryContainerLight,

    tertiary = TertiaryLight,
    onTertiary = OnTertiaryLight,
    tertiaryContainer = TertiaryContainerLight,
    onTertiaryContainer = OnTertiaryContainerLight,

    error = ErrorLight,
    onError = OnErrorLight,
    errorContainer = ErrorContainerLight,
    onErrorContainer = OnErrorContainerLight,

    background = SurfaceLight,
    onBackground = OnSurfaceLight,

    surface = SurfaceLight,
    onSurface = OnSurfaceLight,
    surfaceVariant = SurfaceVariantLight,
    onSurfaceVariant = OnSurfaceVariantLight,

    // Surface Container System (M3 Expressive elevation replacement)
    surfaceDim = SurfaceDimLight,
    surfaceBright = SurfaceBrightLight,
    surfaceContainerLowest = SurfaceContainerLowestLight,
    surfaceContainerLow = SurfaceContainerLowLight,
    surfaceContainer = SurfaceContainerLight,
    surfaceContainerHigh = SurfaceContainerHighLight,
    surfaceContainerHighest = SurfaceContainerHighestLight,

    outline = OutlineLight,
    outlineVariant = OutlineVariantLight,
    scrim = ScrimLight,
    inverseSurface = InverseSurfaceLight,
    inverseOnSurface = InverseOnSurfaceLight,
    inversePrimary = InversePrimaryLight
)

// -----------------------------------------------------------------------------------------
// DARK COLOR SCHEME (M3 Expressive calibrated - protects eyes while maintaining vibrant tones)
// -----------------------------------------------------------------------------------------
private val DarkColorScheme = darkColorScheme(
    primary = PrimaryDark,
    onPrimary = OnPrimaryDark,
    primaryContainer = PrimaryContainerDark,
    onPrimaryContainer = OnPrimaryContainerDark,

    secondary = SecondaryDark,
    onSecondary = OnSecondaryDark,
    secondaryContainer = SecondaryContainerDark,
    onSecondaryContainer = OnSecondaryContainerDark,

    tertiary = TertiaryDark,
    onTertiary = OnTertiaryDark,
    tertiaryContainer = TertiaryContainerDark,
    onTertiaryContainer = OnTertiaryContainerDark,

    error = ErrorDark,
    onError = OnErrorDark,
    errorContainer = ErrorContainerDark,
    onErrorContainer = OnErrorContainerDark,

    background = SurfaceDark,
    onBackground = OnSurfaceDark,

    surface = SurfaceDark,
    onSurface = OnSurfaceDark,
    surfaceVariant = SurfaceVariantDark,
    onSurfaceVariant = OnSurfaceVariantDark,

    // Surface Container System (Dark mode prevents jarring stark black/white contrasts)
    surfaceDim = SurfaceDimDark,
    surfaceBright = SurfaceBrightDark,
    surfaceContainerLowest = SurfaceContainerLowestDark,
    surfaceContainerLow = SurfaceContainerLowDark,
    surfaceContainer = SurfaceContainerDark,
    surfaceContainerHigh = SurfaceContainerHighDark,
    surfaceContainerHighest = SurfaceContainerHighestDark,

    outline = OutlineDark,
    outlineVariant = OutlineVariantDark,
    scrim = ScrimDark,
    inverseSurface = InverseSurfaceDark,
    inverseOnSurface = InverseOnSurfaceDark,
    inversePrimary = InversePrimaryDark
)

/**
 * The Master Theme wrapper for the entire application.
 *
 * @param darkTheme Controls whether Dark or Light mode is active. Defaults to system setting.
 * @param dynamicColor On Android 12+ (API 31+), allows extracting colors directly from user wallpaper.
 *                     Defaults to false so developers can see our hand-crafted M3 Expressive tokens.
 * @param content The composable UI tree to render inside this theme.
 */
@Composable
fun M3ExpressiveTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        // Material You Dynamic Theming (Wallpaper-derived color extraction)
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    // Configure Edge-to-Edge System Bars (Status bar & Navigation bar)
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = android.graphics.Color.TRANSPARENT
            window.navigationBarColor = android.graphics.Color.TRANSPARENT
            val insetsController = WindowCompat.getInsetsController(window, view)
            insetsController.isAppearanceLightStatusBars = !darkTheme
            insetsController.isAppearanceLightNavigationBars = !darkTheme
        }
    }

    // Inject our customized ColorScheme, Typography, and Shapes into Compose's MaterialTheme
    MaterialTheme(
        colorScheme = colorScheme,
        typography = ExpressiveTypography,
        shapes = ExpressiveShapes,
        content = content
    )
}
