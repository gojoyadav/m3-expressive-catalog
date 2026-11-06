package com.example.m3catalog.ui.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Animation
import androidx.compose.material.icons.rounded.Category
import androidx.compose.material.icons.rounded.Checklist
import androidx.compose.material.icons.rounded.Code
import androidx.compose.material.icons.rounded.Explore
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Info
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
 * Represents the catalog destinations within the M3 Expressive app.
 * Each entry holds its route identifier, human-readable title, subtitle, and Material icon.
 *
 * Configured with 4 streamlined, recognizable bottom navigation tabs:
 * 1. Home - Master showcase portal & core design architecture
 * 2. Components - 30+ Material 3 interactive components & guidelines
 * 3. Design Lab - Color schemes, 5-tier surface hierarchy & typography
 * 4. Kotlin Lab - Interactive Kotlin playground & official beginner/intermediate tour
 *
 * Specialized hubs (Actions, Selection, TextInput, Containment, Navigation, Communication,
 * Motion & Shapes, Adaptive Grid) remain directly accessible through the Home portal,
 * sticky Category Switcher, and Navigation Drawer.
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
        title = "Home",
        description = "Expressive overview & core design principles",
        icon = Icons.Rounded.Home,
        isPrimaryBottomNav = false
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
        title = "Design Lab",
        description = "Tonal palettes, surface containers & expressive scale",
        icon = Icons.Rounded.Palette,
        isPrimaryBottomNav = false
    ),
    KOTLIN_LAB(
        route = "kotlin_lab",
        title = "Kotlin Lab",
        description = "Interactive Kotlin Playground & Tour tutorials",
        icon = Icons.Rounded.Code,
        isPrimaryBottomNav = true
    ),
    ABOUT(
        route = "about",
        title = "About",
        description = "Universal About page, developer profile & links",
        icon = Icons.Rounded.Info,
        isPrimaryBottomNav = true
    ),
    MOTION_SHAPES(
        route = "motion_shapes",
        title = "Motion & Shapes",
        description = "Spring physics playground & 35+ shape morphing",
        icon = Icons.Rounded.Animation,
        isPrimaryBottomNav = false
    ),
    ADAPTIVE_LAYOUTS(
        route = "adaptive_layouts",
        title = "Adaptive Grid",
        description = "4dp/8dp spacing, responsive panes & window sizes",
        icon = Icons.Rounded.ViewQuilt,
        isPrimaryBottomNav = false
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
