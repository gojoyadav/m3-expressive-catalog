package com.example.m3catalog.ui.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Category
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Palette
import androidx.compose.material.icons.rounded.ViewQuilt
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * =========================================================================================
 * NAVIGATION DESTINATIONS
 * Represents the primary catalog destinations within the M3 Expressive app.
 * Each entry holds its route identifier, human-readable title, subtitle, and Material icon.
 * =========================================================================================
 */
enum class CatalogDestination(
    val route: String,
    val title: String,
    val description: String,
    val icon: ImageVector
) {
    HOME(
        route = "home",
        title = "Expressive Hub",
        description = "Introduction to M3 Expressive principles & architecture",
        icon = Icons.Rounded.Home
    ),
    TYPOGRAPHY_COLOR(
        route = "typography_color",
        title = "Type & Colors",
        description = "Tonal palettes, surface containers & expressive scale",
        icon = Icons.Rounded.Palette
    ),
    COMPONENTS(
        route = "components",
        title = "Components",
        description = "Buttons, cards, FABs, sliders & interactive elements",
        icon = Icons.Rounded.Category
    ),
    ADAPTIVE_LAYOUTS(
        route = "adaptive_layouts",
        title = "Adaptive Grid",
        description = "4dp/8dp spacing, responsive panes & window sizes",
        icon = Icons.Rounded.ViewQuilt
    )
}
