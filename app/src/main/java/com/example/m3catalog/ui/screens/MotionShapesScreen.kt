package com.example.m3catalog.ui.screens

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Animation
import androidx.compose.material.icons.rounded.AutoAwesome
import androidx.compose.material.icons.rounded.Bolt
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.GraphicEq
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material.icons.rounded.TouchApp
import androidx.compose.material.icons.rounded.Vibration
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathOperation
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.m3catalog.ui.components.BeginnerCalloutCard
import com.example.m3catalog.ui.components.M3ComponentGuidelineCard
import kotlinx.coroutines.launch
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.roundToInt
import kotlin.math.sin

/**
 * =========================================================================================
 * MOTION & EXPRESSIVE SHAPES SCREEN
 * Source of Truth: https://m3.material.io/styles/motion & https://m3.material.io/styles/shape
 *
 * Implements the Material 3 Expressive Motion Physics System:
 * 1. Motion Physics System: Spring-based composite tokens (Damping + Stiffness) replacing legacy
 *    duration and easing curves. Includes interactive real-time spring physics simulator.
 * 2. Expressive Shape Scale: 35+ non-standard organic shapes (Circle, Squircle, Clover, Scallop,
 *    Sunny burst) with dynamic canvas shape morphing.
 * 3. Tactile Haptics & Sound Principles: Coupling spring physics with tactile haptics.
 * =========================================================================================
 */
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MotionShapesScreen(
    modifier: Modifier = Modifier
) {
    val coroutineScope = rememberCoroutineScope()

    // -------------------------------------------------------------------------------------
    // Spring Physics State
    // -------------------------------------------------------------------------------------
    var dampingRatio by remember { mutableFloatStateOf(Spring.DampingRatioMediumBouncy) }
    var stiffness by remember { mutableFloatStateOf(Spring.StiffnessMedium) }
    var selectedPreset by remember { mutableStateOf("Expressive Spatial") }

    val bounceOffset = remember { Animatable(0f) }
    val bounceScale = remember { Animatable(1f) }

    fun triggerSpring() {
        coroutineScope.launch {
            bounceOffset.snapTo(0f)
            bounceScale.snapTo(0.85f)
            launch {
                bounceOffset.animateTo(
                    targetValue = 0f,
                    animationSpec = spring(
                        dampingRatio = dampingRatio,
                        stiffness = stiffness
                    )
                )
            }
            launch {
                bounceScale.animateTo(
                    targetValue = 1f,
                    animationSpec = spring(
                        dampingRatio = dampingRatio,
                        stiffness = stiffness
                    )
                )
            }
        }
    }

    // -------------------------------------------------------------------------------------
    // Shape Morphing State
    // -------------------------------------------------------------------------------------
    var morphProgress by remember { mutableFloatStateOf(0f) }
    var selectedShapeName by remember { mutableStateOf("Clover 4-Corner") }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // ---------------------------------------------------------------------------------
        // HEADER CALLOUT
        // ---------------------------------------------------------------------------------
        item {
            Spacer(modifier = Modifier.height(8.dp))
            BeginnerCalloutCard(
                conceptTitle = "M3 Expressive Motion & Shapes",
                explanation = "Material 3 Expressive replaces traditional duration-and-easing animations with natural spring physics (damping ratio + stiffness). Combined with 35+ organic shapes, components feel alive, tactile, and immediately responsive to user touch."
            )
        }

        // ---------------------------------------------------------------------------------
        // 1. MOTION PHYSICS SYSTEM (SPRINGS PLAYGROUND)
        // ---------------------------------------------------------------------------------
        item {
            M3ComponentGuidelineCard(
                title = "Motion Physics System (Spring Tokens)",
                tag = "M3 Expressive Core",
                category = "Motion System",
                usageSummary = "M3 Expressive moves away from fixed durations (e.g. 300ms cubic-bezier) to continuous spring physics. Spring composite tokens balance Damping (bounciness) and Stiffness (speed), naturally absorbing user interruption without visual clipping.",
                doText = "Use Expressive Spatial springs for positional shifts and Expressive Effects springs for subtle alpha/color fades.",
                dontText = "Do not use high bounciness (Damping < 0.4) on critical text or dense utility screens.",
                anatomyNotes = "Damping: High (0.2), Medium (0.5), Low (0.75), None (1.0). Stiffness: VeryLow (50), Low (200), Medium (1500), High (10000).",
                codeSnippet = """
// M3 Expressive Spring Animation Spec
val springSpec = spring<Float>(
    dampingRatio = Spring.DampingRatioMediumBouncy, // 0.5f
    stiffness = Spring.StiffnessMedium              // 1500f
)

val animatedScale by animateFloatAsState(
    targetValue = if (isPressed) 0.92f else 1.0f,
    animationSpec = springSpec
)
                """.trimIndent()
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // Preset Selectors
                    Text(
                        text = "M3 Expressive Motion Scheme Presets",
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.primary
                    )

                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        listOf(
                            "Expressive Spatial" to (Spring.DampingRatioMediumBouncy to Spring.StiffnessMedium),
                            "Expressive Fast Spatial" to (Spring.DampingRatioMediumBouncy to Spring.StiffnessMediumLow),
                            "Expressive Effects" to (Spring.DampingRatioLowBouncy to Spring.StiffnessMedium),
                            "Standard Utility" to (Spring.DampingRatioNoBouncy to Spring.StiffnessMedium)
                        ).forEach { (name, pair) ->
                            FilterChip(
                                selected = selectedPreset == name,
                                onClick = {
                                    selectedPreset = name
                                    dampingRatio = pair.first
                                    stiffness = pair.second
                                    triggerSpring()
                                },
                                label = { Text(name) },
                                leadingIcon = if (selectedPreset == name) {
                                    { Icon(Icons.Rounded.Check, contentDescription = null, modifier = Modifier.size(16.dp)) }
                                } else null
                            )
                        }
                    }

                    // Interactive Bounce Target Card
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(140.dp)
                            .graphicsLayer {
                                scaleX = bounceScale.value
                                scaleY = bounceScale.value
                            }
                            .clickable { triggerSpring() },
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surfaceContainerHigh
                        ),
                        shape = RoundedCornerShape(24.dp)
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Surface(
                                    modifier = Modifier.size(56.dp),
                                    shape = CircleShape,
                                    color = MaterialTheme.colorScheme.primaryContainer
                                ) {
                                    Box(contentAlignment = Alignment.Center) {
                                        Icon(
                                            imageVector = Icons.Rounded.Animation,
                                            contentDescription = null,
                                            tint = MaterialTheme.colorScheme.onPrimaryContainer,
                                            modifier = Modifier.size(32.dp)
                                        )
                                    }
                                }
                                Text(
                                    text = "Tap to Feel Spring Physics",
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = "Damping: ${(dampingRatio * 100).roundToInt() / 100f} | Stiffness: ${stiffness.roundToInt()}",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }

                    // Damping Ratio Slider
                    Column {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("Damping Ratio (Bounciness)", style = MaterialTheme.typography.bodyMedium)
                            Text(
                                text = when {
                                    dampingRatio <= 0.35f -> "High Bouncy (0.2)"
                                    dampingRatio <= 0.6f -> "Medium Bouncy (0.5)"
                                    dampingRatio <= 0.85f -> "Low Bouncy (0.75)"
                                    else -> "No Bouncy (1.0)"
                                },
                                style = MaterialTheme.typography.labelMedium,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                        Slider(
                            value = dampingRatio,
                            onValueChange = {
                                dampingRatio = it
                                selectedPreset = "Custom"
                            },
                            valueRange = 0.2f..1.0f,
                            onValueChangeFinished = { triggerSpring() }
                        )
                    }

                    // Stiffness Slider
                    Column {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("Stiffness (Speed)", style = MaterialTheme.typography.bodyMedium)
                            Text(
                                text = "${stiffness.roundToInt()}f",
                                style = MaterialTheme.typography.labelMedium,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                        Slider(
                            value = stiffness,
                            onValueChange = {
                                stiffness = it
                                selectedPreset = "Custom"
                            },
                            valueRange = 50f..5000f,
                            onValueChangeFinished = { triggerSpring() }
                        )
                    }

                    Button(
                        onClick = { triggerSpring() },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Icon(Icons.Rounded.PlayArrow, contentDescription = null)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Trigger Spring Pulse")
                    }
                }
            }
        }

        // ---------------------------------------------------------------------------------
        // 2. 35+ EXPRESSIVE SHAPES & MORPHING PLAYGROUND
        // ---------------------------------------------------------------------------------
        item {
            M3ComponentGuidelineCard(
                title = "35+ Expressive Shape Scale & Morphing",
                tag = "M3 Expressive Core",
                category = "Shape System",
                usageSummary = "Material 3 Expressive expands the design system beyond standard rounded rectangles with over 35 organic geometric silhouettes—including 4-corner clovers, 12-point scallops, and sunny bursts—designed to morph smoothly during interactions.",
                doText = "Use expressive shapes on hero focal elements, floating action buttons, avatars, and badge containers.",
                dontText = "Do not use hyper-complex jagged shapes on long reading containers or data tables.",
                anatomyNotes = "Supported shapes: Clover (4-leaf), Scallop (12-corner), Squircle, Pill, Circle, and Sunny Burst.",
                codeSnippet = """
// Expressive Organic Shape Morphing in Compose
Canvas(modifier = Modifier.size(120.dp)) {
    val path = Path().apply {
        // Compute interpolated clover-to-scallop polygon
        val points = 12
        for (i in 0 until points) {
            val angle = i * (2 * PI / points)
            val r = radius * (1f + 0.25f * sin(angle * 4))
            val x = center.x + r * cos(angle)
            val y = center.y + r * sin(angle)
            if (i == 0) moveTo(x, y) else lineTo(x, y)
        }
        close()
    }
    drawPath(path, color = primaryColor)
}
                """.trimIndent()
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // Shape selector chips
                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        listOf(
                            "Clover (4-Corner)" to 0f,
                            "Scallop (12-Corner)" to 0.33f,
                            "Sunny Burst (8-Point)" to 0.66f,
                            "Squircle (Continuous)" to 1f
                        ).forEach { (name, progress) ->
                            FilterChip(
                                selected = selectedShapeName == name,
                                onClick = {
                                    selectedShapeName = name
                                    morphProgress = progress
                                },
                                label = { Text(name) }
                            )
                        }
                    }

                    // Live Morphing Canvas
                    val primaryColor = MaterialTheme.colorScheme.primary
                    val secondaryColor = MaterialTheme.colorScheme.secondaryContainer
                    val onSecondaryColor = MaterialTheme.colorScheme.onSecondaryContainer

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(180.dp)
                            .background(
                                color = MaterialTheme.colorScheme.surfaceContainerLow,
                                shape = RoundedCornerShape(24.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Canvas(modifier = Modifier.size(140.dp)) {
                            val c = Offset(size.width / 2f, size.height / 2f)
                            val baseRadius = size.width * 0.40f

                            val path = Path()
                            val totalSteps = 72
                            val morphFactor = morphProgress // 0f to 1f

                            for (i in 0..totalSteps) {
                                val angle = i * (2.0 * PI / totalSteps)
                                // Interpolate harmonic waves between 4-leaf clover and 12-scallop
                                val wave4 = sin(angle * 4.0).toFloat() * (1f - morphFactor)
                                val wave12 = sin(angle * 8.0).toFloat() * morphFactor * 0.5f
                                val modulation = 1f + 0.22f * (wave4 + wave12)
                                val r = baseRadius * modulation

                                val x = c.x + (r * cos(angle)).toFloat()
                                val y = c.y + (r * sin(angle)).toFloat()

                                if (i == 0) path.moveTo(x, y) else path.lineTo(x, y)
                            }
                            path.close()

                            // Fill
                            drawPath(path = path, color = primaryColor)

                            // Inner organic stroke
                            drawPath(
                                path = path,
                                color = Color.White.copy(alpha = 0.4f),
                                style = Stroke(width = 4.dp.toPx())
                            )
                        }
                    }

                    // Morph Slider
                    Column {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("Continuous Morph Progress", style = MaterialTheme.typography.bodyMedium)
                            Text("${(morphProgress * 100).roundToInt()}%", style = MaterialTheme.typography.labelMedium)
                        }
                        Slider(
                            value = morphProgress,
                            onValueChange = {
                                morphProgress = it
                                selectedShapeName = when {
                                    it < 0.25f -> "Clover (4-Corner)"
                                    it < 0.55f -> "Scallop (12-Corner)"
                                    it < 0.85f -> "Sunny Burst (8-Point)"
                                    else -> "Squircle (Continuous)"
                                }
                            },
                            valueRange = 0f..1f
                        )
                    }
                }
            }
        }

        // ---------------------------------------------------------------------------------
        // 3. TACTILE HAPTICS & SOUND PRINCIPLES
        // ---------------------------------------------------------------------------------
        item {
            M3ComponentGuidelineCard(
                title = "Tactile Haptics & Physical Coupling",
                tag = "M3 Foundations",
                category = "Tactile System",
                usageSummary = "Material 3 Expressive motion is deeply coupled with physical haptics. Micro-spring interactions fire sharp tactile clicks, while large spatial transitions fire softer confirmations to recreate mechanical tactile reality.",
                doText = "Trigger HapticFeedbackType.LongPress or TextHandleMove on state changes that cross threshold limits.",
                dontText = "Do not fire haptic vibrations on continuous drag motion, which causes haptic fatigue.",
                anatomyNotes = "Feedback levels: Tick (subtle slider notches), Click (button press), Heavy Click (destructive confirmation).",
                codeSnippet = """
val haptic = LocalHapticFeedback.current

Button(
    onClick = {
        haptic.performHapticFeedback(HapticFeedbackType.LongPress)
        triggerAction()
    }
) {
    Text("Tactile Action")
}
                """.trimIndent()
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    OutlinedCard(
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(12.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Icon(Icons.Rounded.TouchApp, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                            Text("Micro Tick", style = MaterialTheme.typography.labelLarge, fontWeight = FontWeight.Bold)
                            Text("Slider notches & segment steps", style = MaterialTheme.typography.bodySmall)
                        }
                    }

                    OutlinedCard(
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(12.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Icon(Icons.Rounded.Vibration, contentDescription = null, tint = MaterialTheme.colorScheme.secondary)
                            Text("Spring Click", style = MaterialTheme.typography.labelLarge, fontWeight = FontWeight.Bold)
                            Text("Button press & modal sheet snap", style = MaterialTheme.typography.bodySmall)
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}
