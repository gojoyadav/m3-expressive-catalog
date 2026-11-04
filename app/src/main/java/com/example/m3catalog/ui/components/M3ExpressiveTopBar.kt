package com.example.m3catalog.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DarkMode
import androidx.compose.material.icons.rounded.LightMode
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

/**
 * =========================================================================================
 * M3 EXPRESSIVE TOP APP BAR
 * Source of Truth: https://m3.material.io/components/top-app-bar/overview
 *
 * M3 Expressive Guideline:
 * Expressive top bars use clean surface integration without harsh drop shadows.
 * Elevation is communicated via subtle surface tone shifts on scroll.
 * We include an interactive Dark/Light mode toggle to demonstrate live recomposition!
 * =========================================================================================
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun M3ExpressiveTopBar(
    title: String,
    subtitle: String,
    isDarkMode: Boolean,
    onToggleDarkMode: () -> Unit,
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        title = {
            Column(horizontalAlignment = Alignment.CenterVertically) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        },
        actions = {
            IconButton(onClick = onToggleDarkMode) {
                Icon(
                    imageVector = if (isDarkMode) Icons.Rounded.LightMode else Icons.Rounded.DarkMode,
                    contentDescription = if (isDarkMode) "Switch to Light Mode" else "Switch to Dark Mode",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            // In M3 Expressive, the top bar rests on surfaceContainer or surface
            containerColor = MaterialTheme.colorScheme.surfaceContainer
        )
    )
}
