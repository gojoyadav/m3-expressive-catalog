package com.example.m3catalog.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

/**
 * =========================================================================================
 * MATERIAL 3 EXPRESSIVE TYPOGRAPHY SYSTEM
 * Source of Truth: https://m3.material.io/styles/typography/type-scale-tokens
 *
 * M3 Expressive Typography Principles:
 * 1. Expressive Contrast: Large dramatic headlines paired with highly legible body text.
 *    The typographic scale establishes visual rhythm and personality without requiring
 *    excessive imagery or ornamental graphics.
 * 2. 5 Distinct Typographic Roles:
 *    - Display:  Reserved for short, high-impact numbers and hero statements (Large/Medium/Small).
 *    - Headline: Best for primary section headers and modal titles (Large/Medium/Small).
 *    - Title:    Medium-emphasis dividers, card headers, and list item headlines (Large/Medium/Small).
 *    - Body:     Optimized for sustained reading comfort across multiline paragraphs (Large/Medium/Small).
 *    - Label:    High-density, utility-focused text inside buttons, chips, tabs, and tags (Large/Medium/Small).
 * 3. Proportional Line Heights & Letter Spacing (Tracking):
 *    - Display styles use tighter letter spacing (-0.25sp to 0sp) so massive characters feel cohesive.
 *    - Small labels use positive tracking (+0.1sp to +0.5sp) to maximize legibility at compact sizes.
 * =========================================================================================
 */

val ExpressiveTypography = Typography(
    // -------------------------------------------------------------------------------------
    // DISPLAY STYLES (Hero, large impactful moments)
    // -------------------------------------------------------------------------------------
    displayLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        fontSize = 57.sp,
        lineHeight = 64.sp,
        letterSpacing = (-0.25).sp
    ),
    displayMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        fontSize = 45.sp,
        lineHeight = 52.sp,
        letterSpacing = 0.sp
    ),
    displaySmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.SemiBold,
        fontSize = 36.sp,
        lineHeight = 44.sp,
        letterSpacing = 0.sp
    ),

    // -------------------------------------------------------------------------------------
    // HEADLINE STYLES (Primary section anchors)
    // -------------------------------------------------------------------------------------
    headlineLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.SemiBold,
        fontSize = 32.sp,
        lineHeight = 40.sp,
        letterSpacing = 0.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.SemiBold,
        fontSize = 28.sp,
        lineHeight = 36.sp,
        letterSpacing = 0.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 24.sp,
        lineHeight = 32.sp,
        letterSpacing = 0.sp
    ),

    // -------------------------------------------------------------------------------------
    // TITLE STYLES (Component headers, card titles, app bars)
    // -------------------------------------------------------------------------------------
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.SemiBold,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    titleMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.15.sp
    ),
    titleSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp
    ),

    // -------------------------------------------------------------------------------------
    // BODY STYLES (Reading, multiline paragraphs, descriptive subtitles)
    // -------------------------------------------------------------------------------------
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.25.sp
    ),
    bodySmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.4.sp
    ),

    // -------------------------------------------------------------------------------------
    // LABEL STYLES (Interactive elements, buttons, tabs, input tags)
    // -------------------------------------------------------------------------------------
    labelLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp
    ),
    labelMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
)
