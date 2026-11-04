package com.example.m3catalog.ui.theme

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

/**
 * =========================================================================================
 * MATERIAL 3 EXPRESSIVE SHAPE SCALE & CORNER RADII
 * Source of Truth: https://m3.material.io/styles/shape/overview
 *
 * M3 Expressive Shape Evolution:
 * Traditional designs constrained shapes to modest 4dp-8dp corners, creating utilitarian,
 * boxy aesthetics. M3 Expressive liberates geometry with generous, tactile curves.
 * Surfaces feel organic, inviting touch, and visually communicating nesting relationships:
 *
 * 1. Extra Small (4.dp):  Micro-elements like tags, small badges, text field underline caps.
 * 2. Small (8.dp):        Chips, assistive tokens, snackbars.
 * 3. Medium (12.dp-16.dp):Standard cards, small dialogs, segmented buttons.
 * 4. Large (24.dp):       Expressive cards, navigation drawers, floating sheets.
 * 5. Extra Large (32.dp): Hero banner containers, search bars, expansive modal sheets.
 * 6. Full / Pill:         Floating Action Buttons, pill action buttons, active indicator pills.
 * =========================================================================================
 */

val ExpressiveShapes = Shapes(
    // 4.dp: High-density micro containers
    extraSmall = RoundedCornerShape(4.dp),

    // 8.dp: Crisp interactive tokens (chips, input selectors)
    small = RoundedCornerShape(8.dp),

    // 16.dp: Standard content containers (elevated & filled cards)
    medium = RoundedCornerShape(16.dp),

    // 24.dp: Expressive larger containers (hero cards, bottom sheet top edges)
    large = RoundedCornerShape(24.dp),

    // 32.dp: Expressive hallmark (dramatic curvature for modals, banners, and search islands)
    extraLarge = RoundedCornerShape(32.dp)
)

// Expressive Pill Shape used across M3 buttons and active indicator tracks
val ExpressivePillShape = CircleShape

// Asymmetric & playful corner tokens for custom expressive cards
val ExpressiveTopCurvedShape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp, bottomStart = 8.dp, bottomEnd = 8.dp)
val ExpressiveLeadingCurvedShape = RoundedCornerShape(topStart = 24.dp, bottomStart = 24.dp, topEnd = 8.dp, bottomEnd = 8.dp)
