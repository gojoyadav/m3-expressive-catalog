package com.example.m3catalog.ui.theme

import androidx.compose.ui.graphics.Color

/**
 * =========================================================================================
 * MATERIAL 3 EXPRESSIVE COLOR ROLES & PALETTES
 * Source of Truth: https://m3.material.io/styles/color/the-color-system/color-roles
 *
 * M3 Expressive Evolution:
 * Traditional Material Design relied on drop shadows (elevation in dp) to indicate depth.
 * M3 Expressive shifts to "Tonal Elevation" and the revolutionary "Surface Container System".
 * Surfaces are layered using 5 distinct container tones plus surfaceDim and surfaceBright.
 * This guarantees WCAG 2.1 AA accessibility (4.5:1 for normal text, 3:1 for large text & UI),
 * visual calmness in dark mode (preventing blinding glare), and tactile hierarchy in light mode.
 * =========================================================================================
 */

// -----------------------------------------------------------------------------------------
// PRIMARY KEY COLORS (Indigo / Violet Spectrum - Inspires focus, stability, and intelligence)
// -----------------------------------------------------------------------------------------
val PrimaryLight = Color(0xFF4755B6)          // Dominant brand color for high-emphasis elements
val OnPrimaryLight = Color(0xFFFFFFFF)        // High-contrast content drawn on top of Primary
val PrimaryContainerLight = Color(0xFFDFE0FF) // Tonal container for accented cards & selections
val OnPrimaryContainerLight = Color(0xFF000D5E) // Content drawn on top of PrimaryContainer

val PrimaryDark = Color(0xFFBCC2FF)           // High-luminance desaturated tone for dark mode
val OnPrimaryDark = Color(0xFF132287)         // Deep tone ensuring readability on top of PrimaryDark
val PrimaryContainerDark = Color(0xFF2E3D9E)  // Darkened container retaining brand identity
val OnPrimaryContainerDark = Color(0xFFDFE0FF)

// -----------------------------------------------------------------------------------------
// SECONDARY KEY COLORS (Teal / Cyan Spectrum - Balances Primary with calmness)
// -----------------------------------------------------------------------------------------
val SecondaryLight = Color(0xFF5B5D72)
val OnSecondaryLight = Color(0xFFFFFFFF)
val SecondaryContainerLight = Color(0xFFE0E1F9)
val OnSecondaryContainerLight = Color(0xFF181A2C)

val SecondaryDark = Color(0xFFC4C5DD)
val OnSecondaryDark = Color(0xFF2D2F42)
val SecondaryContainerDark = Color(0xFF434559)
val OnSecondaryContainerDark = Color(0xFFE0E1F9)

// -----------------------------------------------------------------------------------------
// TERTIARY KEY COLORS (Vibrant Coral / Amber - The hallmark of M3 Expressive!)
// M3 Expressive leverages Tertiary colors far more assertively than M2/M3 baseline to create
// joyful focal points, whimsical tags, expressive FAB highlights, and celebratory badges.
// -----------------------------------------------------------------------------------------
val TertiaryLight = Color(0xFF7A5300)
val OnTertiaryLight = Color(0xFFFFFFFF)
val TertiaryContainerLight = Color(0xFFFFDEAC)
val OnTertiaryContainerLight = Color(0xFF271900)

val TertiaryDark = Color(0xFFFFBA54)
val OnTertiaryDark = Color(0xFF412A00)
val TertiaryContainerDark = Color(0xFF5D3E00)
val OnTertiaryContainerDark = Color(0xFFFFDEAC)

// -----------------------------------------------------------------------------------------
// ERROR COLORS (Critical state feedback conforming strictly to accessibility standards)
// -----------------------------------------------------------------------------------------
val ErrorLight = Color(0xFFBA1A1A)
val OnErrorLight = Color(0xFFFFFFFF)
val ErrorContainerLight = Color(0xFFFFDAD6)
val OnErrorContainerLight = Color(0xFF410002)

val ErrorDark = Color(0xFFFFB4AB)
val OnErrorDark = Color(0xFF690005)
val ErrorContainerDark = Color(0xFF93000A)
val OnErrorContainerDark = Color(0xFFFFDAD6)

// -----------------------------------------------------------------------------------------
// SURFACE CONTAINER SYSTEM (The Core M3 Expressive Surface Architecture)
// Instead of shadow blurs, components stack using these 5 container levels:
// - Container Lowest:  Background behind everything (e.g. grouped lists)
// - Container Low:     Bottom sheets, cards resting on canvas
// - Container:         Standard cards, dialog backings
// - Container High:    Prominent interactive elements
// - Container Highest: Elements requiring maximum separation before floating
// -----------------------------------------------------------------------------------------
val SurfaceLight = Color(0xFFFBF8FF)
val OnSurfaceLight = Color(0xFF1B1B21)
val SurfaceVariantLight = Color(0xFFE3E1EC)
val OnSurfaceVariantLight = Color(0xFF46464F)

val SurfaceDimLight = Color(0xFFDBD9E0)
val SurfaceBrightLight = Color(0xFFFBF8FF)
val SurfaceContainerLowestLight = Color(0xFFFFFFFF)
val SurfaceContainerLowLight = Color(0xFFF5F2FA)
val SurfaceContainerLight = Color(0xFFEFEDF4)
val SurfaceContainerHighLight = Color(0xFFEAE7EF)
val SurfaceContainerHighestLight = Color(0xFFE4E1E9)

// Dark Surface Containers (Carefully calibrated to avoid pure #000000 OLED banding)
val SurfaceDark = Color(0xFF131318)
val OnSurfaceDark = Color(0xFFE4E1E9)
val SurfaceVariantDark = Color(0xFF46464F)
val OnSurfaceVariantDark = Color(0xFFC7C5D0)

val SurfaceDimDark = Color(0xFF131318)
val SurfaceBrightDark = Color(0xFF39393F)
val SurfaceContainerLowestDark = Color(0xFF0E0E13)
val SurfaceContainerLowDark = Color(0xFF1B1B21)
val SurfaceContainerDark = Color(0xFF1F1F25)
val SurfaceContainerHighDark = Color(0xFF2A292F)
val SurfaceContainerHighestDark = Color(0xFF35343A)

// -----------------------------------------------------------------------------------------
// OUTLINE & SPECIAL PURPOSE TOKENS
// -----------------------------------------------------------------------------------------
val OutlineLight = Color(0xFF767680)        // Clean stroke separation for OutlinedCards & Buttons
val OutlineVariantLight = Color(0xFFC7C5D0) // Subtle dividers and disabled borders
val ScrimLight = Color(0xFF000000)          // Backdrop veil for modal sheets & dialogs

val OutlineDark = Color(0xFF90909A)
val OutlineVariantDark = Color(0xFF46464F)
val ScrimDark = Color(0xFF000000)

val InverseSurfaceLight = Color(0xFF303036)
val InverseOnSurfaceLight = Color(0xFFF2F0F7)
val InversePrimaryLight = Color(0xFFBCC2FF)

val InverseSurfaceDark = Color(0xFFE4E1E9)
val InverseOnSurfaceDark = Color(0xFF303036)
val InversePrimaryDark = Color(0xFF4755B6)
