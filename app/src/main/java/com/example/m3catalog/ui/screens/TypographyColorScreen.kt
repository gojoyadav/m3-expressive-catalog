package com.example.m3catalog.ui.screens

import androidx.compose.foundation.Canvas
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.example.m3catalog.ui.components.BeginnerCalloutCard

/**
 * =========================================================================================
 * TYPOGRAPHY & COLOR SYSTEM SCREEN
 * Source of Truth: https://m3.material.io/styles/color/overview
 *                  https://m3.material.io/styles/typography/overview
 *
 * This screen illustrates:
 * 1. The 15 Expressive Typography Roles (Display, Headline, Title, Body, Label).
 * 2. Key Color Roles (Primary, Secondary, Tertiary, Error).
 * 3. The 5-level Surface Container System (Lowest to Highest) + Dim / Bright.
 * 4. A Native Canvas Visualization rendering tonal spectrums and corner morphing!
 * =========================================================================================
 */
@Composable
fun TypographyColorScreen(
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
            Text(
                text = "Expressive Color Architecture",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = "Dynamic roles designed for accessibility, calmness, and visual delight.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        item {
            BeginnerCalloutCard(
                conceptTitle = "Why Surface Containers replace drop shadows",
                explanation = "In M2, elevation was simulated using black drop shadows (e.g. 8.dp elevation). In Dark Mode, black drop shadows become invisible against a black background! M3 Expressive solves this with 'Surface Containers'. Each layer uses a calibrated tone so depth is crisp in both Light and Dark modes."
            )
        }

        item {
            SurfaceContainerHierarchyCard()
        }

        item {
            CoreColorRolesCard()
        }

        item {
            Text(
                text = "Native Canvas Tonal Visualizer",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = "Rendering graphics natively via Canvas drawRoundRect and stroke primitives.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        item {
            ExpressiveCanvasVisualizer()
        }

        item {
            Text(
                text = "Expressive Typography Scale",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = "High-contrast scales giving your Android app voice and personality.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        item {
            TypographyScaleCard()
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun SurfaceContainerHierarchyCard() {
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
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(
                text = "Surface Container System",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )

            SurfaceContainerRow("Surface Container Lowest", MaterialTheme.colorScheme.surfaceContainerLowest)
            SurfaceContainerRow("Surface Container Low", MaterialTheme.colorScheme.surfaceContainerLow)
            SurfaceContainerRow("Surface Container (Default)", MaterialTheme.colorScheme.surfaceContainer)
            SurfaceContainerRow("Surface Container High", MaterialTheme.colorScheme.surfaceContainerHigh)
            SurfaceContainerRow("Surface Container Highest", MaterialTheme.colorScheme.surfaceContainerHighest)
        }
    }
}

@Composable
private fun SurfaceContainerRow(name: String, color: Color) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color, shape = RoundedCornerShape(8.dp))
            .border(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f), RoundedCornerShape(8.dp))
            .padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = name,
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onSurface
        )
        Box(
            modifier = Modifier
                .size(20.dp)
                .background(color, shape = RoundedCornerShape(4.dp))
                .border(1.dp, MaterialTheme.colorScheme.outline, RoundedCornerShape(4.dp))
        )
    }
}

@Composable
private fun CoreColorRolesCard() {
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
            Text(
                text = "Key Color Roles & Pairings",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )

            ColorPairingRow("Primary (Dominant Brand)", MaterialTheme.colorScheme.primary, MaterialTheme.colorScheme.onPrimary)
            ColorPairingRow("Secondary (Calm Accents)", MaterialTheme.colorScheme.secondary, MaterialTheme.colorScheme.onSecondary)
            ColorPairingRow("Tertiary (Expressive Pop!)", MaterialTheme.colorScheme.tertiary, MaterialTheme.colorScheme.onTertiary)
            ColorPairingRow("Error (Accessibility Feedback)", MaterialTheme.colorScheme.error, MaterialTheme.colorScheme.onError)
        }
    }
}

@Composable
private fun ColorPairingRow(label: String, bg: Color, fg: Color) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(bg, shape = MaterialTheme.shapes.small)
            .padding(horizontal = 16.dp, vertical = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium,
            color = fg
        )
        Text(
            text = "Accessible Contrast",
            style = MaterialTheme.typography.labelSmall,
            color = fg.copy(alpha = 0.85f)
        )
    }
}

/**
 * Native Canvas Visualizer demonstrating how to draw custom M3 Expressive
 * geometric curves and tonal gradients directly using Android primitives.
 */
@Composable
private fun ExpressiveCanvasVisualizer() {
    val primaryColor = MaterialTheme.colorScheme.primary
    val tertiaryColor = MaterialTheme.colorScheme.tertiary
    val surfaceHighColor = MaterialTheme.colorScheme.surfaceContainerHigh
    val outlineColor = MaterialTheme.colorScheme.outline

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(containerColor = surfaceHighColor)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Canvas(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp)
            ) {
                val canvasWidth = size.width
                val canvasHeight = size.height

                // 1. Draw Expressive Outer Nesting Container
                drawRoundRect(
                    color = primaryColor.copy(alpha = 0.12f),
                    size = Size(canvasWidth, canvasHeight),
                    cornerRadius = CornerRadius(28.dp.toPx(), 28.dp.toPx())
                )

                // 2. Draw Expressive Primary Inner Pill
                val pillWidth = canvasWidth * 0.55f
                val pillHeight = canvasHeight * 0.45f
                drawRoundRect(
                    color = primaryColor,
                    topLeft = Offset((canvasWidth - pillWidth) / 2f, (canvasHeight - pillHeight) / 2f),
                    size = Size(pillWidth, pillHeight),
                    cornerRadius = CornerRadius(pillHeight / 2f, pillHeight / 2f)
                )

                // 3. Draw Expressive Tertiary Accent Indicator
                drawCircle(
                    color = tertiaryColor,
                    radius = 14.dp.toPx(),
                    center = Offset(canvasWidth * 0.78f, canvasHeight * 0.5f)
                )

                // 4. Draw Precision Border Stroke
                drawRoundRect(
                    color = outlineColor.copy(alpha = 0.4f),
                    size = Size(canvasWidth, canvasHeight),
                    cornerRadius = CornerRadius(28.dp.toPx(), 28.dp.toPx()),
                    style = Stroke(width = 2.dp.toPx())
                )
            }

            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Native Canvas Primitives: Custom Corner Curvature & Tonal Accents",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun TypographyScaleCard() {
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
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            TypeScaleSample("Display Large (57sp)", MaterialTheme.typography.displayLarge)
            TypeScaleSample("Display Small (36sp)", MaterialTheme.typography.displaySmall)
            TypeScaleSample("Headline Large (32sp)", MaterialTheme.typography.headlineLarge)
            TypeScaleSample("Headline Small (24sp)", MaterialTheme.typography.headlineSmall)
            TypeScaleSample("Title Large (22sp)", MaterialTheme.typography.titleLarge)
            TypeScaleSample("Title Medium (16sp)", MaterialTheme.typography.titleMedium)
            TypeScaleSample("Body Large (16sp)", MaterialTheme.typography.bodyLarge)
            TypeScaleSample("Body Small (12sp)", MaterialTheme.typography.bodySmall)
            TypeScaleSample("Label Large (14sp)", MaterialTheme.typography.labelLarge)
            TypeScaleSample("Label Small (11sp)", MaterialTheme.typography.labelSmall)
        }
    }
}

@Composable
private fun TypeScaleSample(label: String, style: TextStyle) {
    Column {
        Text(
            text = label,
            style = style,
            color = MaterialTheme.colorScheme.onSurface
        )
        Text(
            text = "Size: ${style.fontSize.value.toInt()}sp | LineHeight: ${style.lineHeight.value.toInt()}sp",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.primary
        )
    }
}
