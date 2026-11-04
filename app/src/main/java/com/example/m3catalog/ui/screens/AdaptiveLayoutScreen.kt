package com.example.m3catalog.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AspectRatio
import androidx.compose.material.icons.rounded.Smartphone
import androidx.compose.material.icons.rounded.Tablet
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.m3catalog.ui.components.BeginnerCalloutCard
import kotlin.math.roundToInt

/**
 * =========================================================================================
 * ADAPTIVE LAYOUTS & SPACING GRID SCREEN
 * Source of Truth: https://m3.material.io/foundations/layout/understanding-layout/overview
 *                  https://developer.android.com/develop/ui/compose/layouts/adaptive
 *
 * M3 Expressive Layout Principles:
 * 1. 4dp Baseline Grid:
 *    All paddings, margins, and component dimensions align strictly to multiples of 4dp.
 *    Standard rhythm tokens: 4dp, 8dp, 12dp, 16dp, 24dp, 32dp, 48dp, 64dp.
 *
 * 2. Window Size Classes:
 *    Instead of checking for specific device models, Compose categorizes screen width into:
 *    - Compact (< 600dp): Standard phones (single column, bottom navigation)
 *    - Medium (600dp - 840dp): Small tablets, unfoldables (2-column flow, navigation rail)
 *    - Expanded (> 840dp): Large tablets, desktop, split-screen (multi-pane, side rail)
 * =========================================================================================
 */
@Composable
fun AdaptiveLayoutScreen(
    modifier: Modifier = Modifier
) {
    // Interactive screen width simulator (allows exploring adaptive behavior live!)
    var simulatedWidthDp by remember { mutableFloatStateOf(420f) }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Adaptive Layouts & Grid",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = "Dynamic content scaling across phones, foldables, and tablets.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        item {
            BeginnerCalloutCard(
                conceptTitle = "What are Window Size Classes?",
                explanation = "Never design solely for one screen size! In modern Android, apps run on tiny 320dp phones, foldables that open mid-session, and 12-inch tablets. WindowSizeClasses group widths into Compact (<600dp), Medium (600-840dp), and Expanded (>840dp). Below, drag the simulator slider to see how the layout responds in real time!"
            )
        }

        // ---------------------------------------------------------------------------------
        // 1. LIVE SCREEN WIDTH SIMULATOR
        // ---------------------------------------------------------------------------------
        item {
            InteractiveWidthSimulatorCard(
                simulatedWidth = simulatedWidthDp,
                onWidthChange = { simulatedWidthDp = it }
            )
        }

        // ---------------------------------------------------------------------------------
        // 2. THE 4DP/8DP SPACING RHYTHM
        // ---------------------------------------------------------------------------------
        item {
            Text(
                text = "The M3 4dp/8dp Spacing System",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
        }

        item {
            SpacingGridCard()
        }

        // ---------------------------------------------------------------------------------
        // 3. WINDOW CLASS METRICS SUMMARY
        // ---------------------------------------------------------------------------------
        item {
            Text(
                text = "Window Size Class Specifications",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
        }

        item {
            WindowClassSpecCard()
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun InteractiveWidthSimulatorCard(
    simulatedWidth: Float,
    onWidthChange: (Float) -> Unit
) {
    val widthInt = simulatedWidth.roundToInt()
    val isWideScreen = widthInt >= 600

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Simulated Width: ${widthInt}dp",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = if (isWideScreen) "Medium / Expanded" else "Compact Phone",
                    style = MaterialTheme.typography.labelMedium,
                    color = if (isWideScreen) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.secondary
                )
            }

            Slider(
                value = simulatedWidth,
                onValueChange = onWidthChange,
                valueRange = 320f..860f
            )

            Text(
                text = "Live Layout Response:",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            // Dynamic adaptive layout preview:
            // When simulated width >= 600dp -> Render 2-column side-by-side layout
            // When simulated width < 600dp  -> Render 1-column stacked layout
            if (isWideScreen) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    DemoPane(
                        title = "Pane A (Primary Stream)",
                        subtitle = "Adaptive multi-column layout on wider screens.",
                        modifier = Modifier.weight(1f)
                    )
                    DemoPane(
                        title = "Pane B (Detail Inspector)",
                        subtitle = "Simultaneous detail view enabled without back navigation.",
                        modifier = Modifier.weight(1f)
                    )
                }
            } else {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    DemoPane(
                        title = "Single Column Feed",
                        subtitle = "Standard stacked hierarchy optimized for one-handed phone use.",
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}

@Composable
private fun DemoPane(title: String, subtitle: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .background(
                color = MaterialTheme.colorScheme.surfaceContainerHigh,
                shape = MaterialTheme.shapes.medium
            )
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.outlineVariant,
                shape = MaterialTheme.shapes.medium
            )
            .padding(14.dp)
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(title, style = MaterialTheme.typography.titleSmall, color = MaterialTheme.colorScheme.primary)
            Text(subtitle, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}

@Composable
private fun SpacingGridCard() {
    val spacingTokens = listOf(
        Pair("4.dp", "Micro adjustments, icon gaps"),
        Pair("8.dp", "Chip padding, compact margins"),
        Pair("12.dp", "Card internal spacing"),
        Pair("16.dp", "Standard screen margins & content gutters"),
        Pair("24.dp", "Large section dividers"),
        Pair("32.dp", "Hero container padding"),
        Pair("48.dp", "Screen boundary anchors")
    )

    OutlinedCard(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            spacingTokens.forEach { (token, desc) ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = token,
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = desc,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

@Composable
private fun WindowClassSpecCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerLow
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            WindowClassRow(
                icon = Icons.Rounded.Smartphone,
                title = "Compact (< 600dp)",
                navigation = "Bottom NavigationBar",
                columns = "4 Columns (16dp margins)"
            )
            WindowClassRow(
                icon = Icons.Rounded.Tablet,
                title = "Medium (600dp - 840dp)",
                navigation = "NavigationRail",
                columns = "8 Columns (24dp margins)"
            )
            WindowClassRow(
                icon = Icons.Rounded.AspectRatio,
                title = "Expanded (> 840dp)",
                navigation = "Navigation Drawer / Rail",
                columns = "12 Columns (24dp margins)"
            )
        }
    }
}

@Composable
private fun WindowClassRow(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    navigation: String,
    columns: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(24.dp)
        )
        Column {
            Text(title, style = MaterialTheme.typography.titleSmall, color = MaterialTheme.colorScheme.onSurface)
            Text("Nav: $navigation | Grid: $columns", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}
