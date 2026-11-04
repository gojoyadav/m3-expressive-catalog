package com.example.m3catalog.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Lightbulb
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * =========================================================================================
 * BEGINNER CALLOUT CARD
 * A reusable pedagogical UI component designed to explain core Jetpack Compose and
 * Material 3 concepts directly inside the interactive catalog.
 *
 * Concepts explained here:
 * - What is a Composable function?
 *   A function marked with `@Composable` transforms data into UI elements declaratively.
 *   Unlike traditional imperative Android UI (where you'd call `textView.setText(...)`),
 *   in Compose you simply say `Text(text = "...")` and let Compose render it.
 *
 * - What is a Modifier?
 *   Modifiers are ordered decorating instructions. They configure layout size, padding,
 *   background, borders, clicks, and accessibility semantics.
 * =========================================================================================
 */
@Composable
fun BeginnerCalloutCard(
    conceptTitle: String,
    explanation: String,
    modifier: Modifier = Modifier
) {
    OutlinedCard(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = MaterialTheme.shapes.medium, // 16.dp rounded corners
        colors = CardDefaults.outlinedCardColors(
            // Use Surface Container Low for subtle, non-distracting educational callouts
            containerColor = MaterialTheme.colorScheme.surfaceContainerLow
        ),
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.5f)
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Rounded.Lightbulb,
                    contentDescription = "Concept Tip",
                    tint = MaterialTheme.colorScheme.tertiary,
                    modifier = Modifier.size(24.dp)
                )
                Text(
                    text = conceptTitle,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            Text(
                text = explanation,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
