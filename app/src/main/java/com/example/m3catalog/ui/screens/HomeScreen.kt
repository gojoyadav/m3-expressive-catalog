package com.example.m3catalog.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.material.icons.rounded.AutoAwesome
import androidx.compose.material.icons.rounded.Category
import androidx.compose.material.icons.rounded.Checklist
import androidx.compose.material.icons.rounded.Code
import androidx.compose.material.icons.rounded.Explore
import androidx.compose.material.icons.rounded.Layers
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material.icons.rounded.Palette
import androidx.compose.material.icons.rounded.TextFields
import androidx.compose.material.icons.rounded.TouchApp
import androidx.compose.material.icons.rounded.ViewQuilt
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.m3catalog.ui.components.BeginnerCalloutCard
import com.example.m3catalog.ui.model.CatalogDestination

/**
 * =========================================================================================
 * EXPRESSIVE HUB: HOME SCREEN
 * The welcome portal and interactive guide to the Material 3 Expressive architecture.
 * =========================================================================================
 */
@Composable
fun HomeScreen(
    onNavigate: (CatalogDestination) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(8.dp))
            HeroExpressiveBanner()
        }

        item {
            BeginnerCalloutCard(
                conceptTitle = "Declarative UI vs Imperative XML",
                explanation = "In traditional Android (XML), you mutate views directly. In Jetpack Compose, the UI is a pure mathematical function of state: UI = f(State). When state changes, Compose automatically re-executes only the necessary Composables via Recomposition."
            )
        }

        item {
            Text(
                text = "Explore the M3 Component Suite",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
        }

        // 1. Actions & Buttons
        item {
            CatalogNavigationCard(
                title = "1. Actions & Buttons",
                subtitle = "5 button sizes (XS-XL), split buttons, segmented buttons & FABs.",
                icon = Icons.Rounded.TouchApp,
                accentColor = MaterialTheme.colorScheme.primary,
                onClick = { onNavigate(CatalogDestination.ACTIONS) }
            )
        }

        // 2. Selection & Inputs
        item {
            CatalogNavigationCard(
                title = "2. Selection & Inputs",
                subtitle = "Checkboxes (tri-state), radio groups, switches, chips & range sliders.",
                icon = Icons.Rounded.Checklist,
                accentColor = MaterialTheme.colorScheme.tertiary,
                onClick = { onNavigate(CatalogDestination.SELECTION) }
            )
        }

        // 3. Text Fields & Search
        item {
            CatalogNavigationCard(
                title = "3. Text Fields & Search",
                subtitle = "Filled/outlined text fields, password masking & interactive search bars.",
                icon = Icons.Rounded.TextFields,
                accentColor = MaterialTheme.colorScheme.secondary,
                onClick = { onNavigate(CatalogDestination.TEXT_INPUT) }
            )
        }

        // 4. Containment & Sheets
        item {
            CatalogNavigationCard(
                title = "4. Containment & Sheets",
                subtitle = "Cards, alert dialogs, modal bottom sheets, menus & tooltips.",
                icon = Icons.Rounded.Layers,
                accentColor = MaterialTheme.colorScheme.primary,
                onClick = { onNavigate(CatalogDestination.CONTAINMENT) }
            )
        }

        // 5. Navigation & App Bars
        item {
            CatalogNavigationCard(
                title = "5. Navigation & App Bars",
                subtitle = "4 top app bar styles, bottom app bars, rails, drawers & tabs.",
                icon = Icons.Rounded.Explore,
                accentColor = MaterialTheme.colorScheme.tertiary,
                onClick = { onNavigate(CatalogDestination.NAVIGATION) }
            )
        }

        // 6. Feedback & Pickers
        item {
            CatalogNavigationCard(
                title = "6. Feedback & Pickers",
                subtitle = "Dot/numeric badges, progress bars, snackbars, date & time pickers.",
                icon = Icons.Rounded.Notifications,
                accentColor = MaterialTheme.colorScheme.secondary,
                onClick = { onNavigate(CatalogDestination.COMMUNICATION) }
            )
        }

        // 7. Typography & Expressive Colors
        item {
            CatalogNavigationCard(
                title = "7. Typography & Colors",
                subtitle = "Tonal palettes, 5 Surface Containers, Canvas visualizer & type tokens.",
                icon = Icons.Rounded.Palette,
                accentColor = MaterialTheme.colorScheme.primary,
                onClick = { onNavigate(CatalogDestination.TYPOGRAPHY_COLOR) }
            )
        }

        // 8. Adaptive Layouts & Spacing Grid
        item {
            CatalogNavigationCard(
                title = "8. Adaptive Grid & Panes",
                subtitle = "4dp/8dp baseline grid, responsive window classes & multi-pane layouts.",
                icon = Icons.Rounded.ViewQuilt,
                accentColor = MaterialTheme.colorScheme.tertiary,
                onClick = { onNavigate(CatalogDestination.ADAPTIVE_LAYOUTS) }
            )
        }

        // 9. All Components Overview
        item {
            CatalogNavigationCard(
                title = "9. Component Gallery Overview",
                subtitle = "Combined view and quick reference across components.",
                icon = Icons.Rounded.Category,
                accentColor = MaterialTheme.colorScheme.secondary,
                onClick = { onNavigate(CatalogDestination.COMPONENTS) }
            )
        }

        item {
            CorePillarsCard()
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun HeroExpressiveBanner() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.extraLarge,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primary),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Rounded.AutoAwesome,
                        contentDescription = "Expressive Star",
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
                Column {
                    Text(
                        text = "Material 3 Expressive",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                    Text(
                        text = "The Modern Visual Language",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.8f)
                    )
                }
            }

            Text(
                text = "Welcome to your living interactive encyclopedia. Material 3 Expressive evolves beyond rigid boxes and harsh shadows with playful curves, tonal depth, bold typography, and physics-driven personality.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
    }
}

@Composable
private fun CatalogNavigationCard(
    title: String,
    subtitle: String,
    icon: ImageVector,
    accentColor: androidx.compose.ui.graphics.Color,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerHigh
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(MaterialTheme.shapes.medium)
                    .background(accentColor.copy(alpha = 0.15f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = accentColor,
                    modifier = Modifier.size(28.dp)
                )
            }

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Icon(
                imageVector = Icons.Rounded.ArrowForward,
                contentDescription = "Navigate",
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun CorePillarsCard() {
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
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Architectural Pillars",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )

            PillarRow(
                icon = Icons.Rounded.Layers,
                title = "Surface Container System",
                desc = "5 elevation containers (Lowest, Low, Default, High, Highest) replace arbitrary drop shadows with accessible tonal hierarchy."
            )

            PillarRow(
                icon = Icons.Rounded.Code,
                title = "Pure Native Compose",
                desc = "Built 100% on native Kotlin primitives, StateFlow, Canvas graphics, and zero third-party dependencies."
            )
        }
    }
}

@Composable
private fun PillarRow(icon: ImageVector, title: String, desc: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(20.dp)
        )
        Column {
            Text(
                text = title,
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = desc,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
