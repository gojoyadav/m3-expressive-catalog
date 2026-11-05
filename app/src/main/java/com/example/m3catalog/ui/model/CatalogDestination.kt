package com.example.m3catalog.ui.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Category
import androidx.compose.material.icons.rounded.Checklist
import androidx.compose.material.icons.rounded.Explore
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Layers
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material.icons.rounded.Palette
import androidx.compose.material.icons.rounded.TextFields
import androidx.compose.material.icons.rounded.TouchApp
import androidx.compose.material.icons.rounded.ViewQuilt
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * =========================================================================================
 * NAVIGATION DESTINATIONS
 * Represents the primary catalog destinations within the M3 Expressive app.
 * Each entry holds its route identifier, human-readable title, subtitle, and Material icon.
 * Fully aligned with the 6 functional categories of https://m3.material.io/components
 * plus foundational design system labs.
 *
 * Keeps the original 4 bottom navigation tabs (Home, Components, Type & Colors, Adaptive Grid)
 * for 100% fidelity to the original app experience, with expanded hubs accessible via
 * Drawer, Home portal, and the Components Category Switcher.
 * =========================================================================================
 */
enum class CatalogDestination(
    val route: String,
    val title: String,
    val description: String,
    val icon: ImageVector,
    val isPrimaryBottomNav: Boolean = false
) {
    HOME(
        route = "home",
        title = "Expressive Hub",
        description = "Introduction to M3 Expressive principles & architecture",
        icon = Icons.Rounded.Home,
        isPrimaryBottomNav = true
    ),
    COMPONENTS(
        route = "components",
        title = "Components",
        description = "Interactive gallery overview & category guideline hub",
        icon = Icons.Rounded.Category,
        isPrimaryBottomNav = true
    ),
    TYPOGRAPHY_COLOR(
        route = "typography_color",
        title = "Type & Colors",
        description = "Tonal palettes, surface containers & expressive scale",
        icon = Icons.Rounded.Palette,
        isPrimaryBottomNav = true
    ),
    ADAPTIVE_LAYOUTS(
        route = "adaptive_layouts",
        title = "Adaptive Grid",
        description = "4dp/8dp spacing, responsive panes & window sizes",
        icon = Icons.Rounded.ViewQuilt,
        isPrimaryBottomNav = true
    ),
    ACTIONS(
        route = "actions",
        title = "Actions & Buttons",
        description = "5 button sizes, split buttons, segmented buttons & FABs",
        icon = Icons.Rounded.TouchApp,
        isPrimaryBottomNav = false
    ),
    SELECTION(
        route = "selection",
        title = "Selection & Inputs",
        description = "Checkboxes, radio groups, switches, chips & range sliders",
        icon = Icons.Rounded.Checklist,
        isPrimaryBottomNav = false
    ),
    TEXT_INPUT(
        route = "text_input",
        title = "Text Fields & Search",
        description = "Filled/outlined text fields, prefix/suffix & search bars",
        icon = Icons.Rounded.TextFields,
        isPrimaryBottomNav = false
    ),
    CONTAINMENT(
        route = "containment",
        title = "Containment & Sheets",
        description = "Cards, dialogs, bottom/side sheets, menus & tooltips",
        icon = Icons.Rounded.Layers,
        isPrimaryBottomNav = false
    ),
    NAVIGATION(
        route = "navigation",
        title = "Navigation & Bars",
        description = "Top app bars, bottom app bars, rails, drawers & tabs",
        icon = Icons.Rounded.Explore,
        isPrimaryBottomNav = false
    ),
    COMMUNICATION(
        route = "communication",
        title = "Feedback & Pickers",
        description = "Badges, progress bars, snackbars, date & time pickers",
        icon = Icons.Rounded.Notifications,
        isPrimaryBottomNav = false
    )
}
