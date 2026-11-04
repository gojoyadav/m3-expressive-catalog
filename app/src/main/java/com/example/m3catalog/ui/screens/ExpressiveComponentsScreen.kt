package com.example.m3catalog.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Send
import androidx.compose.material.icons.rounded.Tune
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FilledCard
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Slider
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.m3catalog.ui.components.BeginnerCalloutCard
import kotlin.math.roundToInt

/**
 * =========================================================================================
 * EXPRESSIVE COMPONENTS GALLERY
 * Source of Truth: https://m3.material.io/components
 *
 * This screen demonstrates the modern M3 component suite:
 * - 5 Expressive Button Variants & Filter Chips
 * - 3 Card Types (Elevated, Filled, Outlined) featuring expanded corner curvature
 * - 4 Floating Action Button sizes (Small, Standard, Large, Extended)
 * - Continuous & Discrete Stepped Sliders
 *
 * BEGINNER LESSONS INSIDE:
 * - `remember { mutableStateOf(...) }` for local UI state
 * - How Modifier chaining works (Order matters!)
 * =========================================================================================
 */
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ExpressiveComponentsScreen(
    modifier: Modifier = Modifier
) {
    // -------------------------------------------------------------------------------------
    // BEGINNER CONCEPT: LOCAL COMPOSABLE STATE WITH remember & mutableStateOf
    // In Kotlin, a normal variable `var clicks = 0` would be reset to 0 every single time
    // this composable recomposes (re-runs)!
    // `remember` tells Compose: "Store this value across recompositions."
    // `mutableIntStateOf` tells Compose: "Notify me when this value changes so I can redraw!"
    // -------------------------------------------------------------------------------------
    var localCounter by remember { mutableIntStateOf(0) }
    var continuousSliderValue by remember { mutableFloatStateOf(65f) }
    var discreteSliderValue by remember { mutableFloatStateOf(4f) }
    var selectedChip by remember { mutableStateOf("Design") }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Expressive Components",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = "Tactile touch targets, playful geometry, and accessible states.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        item {
            BeginnerCalloutCard(
                conceptTitle = "Crucial Beginner Rule: Modifier Order Matters!",
                explanation = "Compose executes modifiers in strict top-to-bottom sequence! For instance: 'Modifier.padding(16.dp).clickable { }' puts the click ripple INSIDE the padding. But 'Modifier.clickable { }.padding(16.dp)' expands the clickable ripple to cover the entire area including padding! Always think of Modifiers as wrapping the component from outside in."
            )
        }

        // ---------------------------------------------------------------------------------
        // 1. BUTTONS SECTION
        // ---------------------------------------------------------------------------------
        item {
            Text(
                text = "1. Buttons & Action Hierarchy",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
        }

        item {
            ButtonsShowcaseCard(
                counter = localCounter,
                onIncrement = { localCounter++ }
            )
        }

        item {
            FilterChipsRow(
                selectedChip = selectedChip,
                onChipSelected = { selectedChip = it }
            )
        }

        // ---------------------------------------------------------------------------------
        // 2. CARDS SECTION
        // ---------------------------------------------------------------------------------
        item {
            Text(
                text = "2. Cards: Surface Container Elevation",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
        }

        item {
            CardsShowcaseSection()
        }

        // ---------------------------------------------------------------------------------
        // 3. FLOATING ACTION BUTTONS (FABs)
        // ---------------------------------------------------------------------------------
        item {
            Text(
                text = "3. Floating Action Buttons (FABs)",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
        }

        item {
            FabsShowcaseCard()
        }

        // ---------------------------------------------------------------------------------
        // 4. SLIDERS & CONTROLS
        // ---------------------------------------------------------------------------------
        item {
            Text(
                text = "4. Sliders & Continuous Controls",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
        }

        item {
            SlidersShowcaseCard(
                continuousValue = continuousSliderValue,
                onContinuousChange = { continuousSliderValue = it },
                discreteValue = discreteSliderValue,
                onDiscreteChange = { discreteSliderValue = it }
            )
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun ButtonsShowcaseCard(
    counter: Int,
    onIncrement: () -> Unit
) {
    ElevatedCard(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.elevatedCardColors(
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
                text = "Interactive Taps: $counter",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )

            // FlowRow automatically wraps buttons to the next line on smaller screens!
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Filled Button: Highest visual emphasis
                Button(onClick = onIncrement) {
                    Icon(Icons.Rounded.Add, contentDescription = null, modifier = Modifier.size(18.dp))
                    Spacer(modifier = Modifier.size(6.dp))
                    Text("Filled")
                }

                // Filled Tonal: Medium-high emphasis using secondary container
                FilledTonalButton(onClick = onIncrement) {
                    Text("Filled Tonal")
                }

                // Elevated Button: Tonal lift
                ElevatedButton(onClick = onIncrement) {
                    Text("Elevated")
                }

                // Outlined Button: Medium-low emphasis with clean stroke
                OutlinedButton(onClick = onIncrement) {
                    Text("Outlined")
                }

                // Text Button: Lowest emphasis for subtle actions
                TextButton(onClick = onIncrement) {
                    Text("Text")
                }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun FilterChipsRow(
    selectedChip: String,
    onChipSelected: (String) -> Unit
) {
    val chips = listOf("Design", "Tokens", "Motion", "Adaptive")
    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        chips.forEach { chipName ->
            val isSelected = selectedChip == chipName
            FilterChip(
                selected = isSelected,
                onClick = { onChipSelected(chipName) },
                label = { Text(chipName) },
                leadingIcon = if (isSelected) {
                    {
                        Icon(
                            imageVector = Icons.Rounded.Check,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                } else null
            )
        }
    }
}

@Composable
private fun CardsShowcaseSection() {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        // 1. Elevated Card (rests on Surface Container Low with soft tonal elevation)
        ElevatedCard(
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.large // 24dp curve
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Elevated Card (24dp Curvature)", style = MaterialTheme.typography.titleMedium)
                Text(
                    "Provides soft tonal separation; ideal for individual items in a scrolling stream.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        // 2. Filled Card (matches Surface Container Highest for strong visual grouping)
        FilledCard(
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.large,
            colors = CardDefaults.filledCardColors(
                containerColor = MaterialTheme.colorScheme.surfaceContainerHighest
            )
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Filled Card (Surface Container Highest)", style = MaterialTheme.typography.titleMedium)
                Text(
                    "High tonal contrast without drop shadows; creates distinct visual zones.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        // 3. Outlined Card (delivers crisp 1dp outline with transparent or surface container backing)
        OutlinedCard(
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.large
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Outlined Card (Crisp Minimalist)", style = MaterialTheme.typography.titleMedium)
                Text(
                    "Used when content density is high and heavy fills would look cluttered.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
private fun FabsShowcaseCard() {
    FilledCard(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.filledCardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerLow
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "M3 Expressive FAB Tier",
                style = MaterialTheme.typography.titleMedium
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Small FAB (40dp)
                SmallFloatingActionButton(onClick = {}) {
                    Icon(Icons.Rounded.Edit, contentDescription = "Edit")
                }

                // Standard FAB (56dp)
                FloatingActionButton(onClick = {}) {
                    Icon(Icons.Rounded.Favorite, contentDescription = "Favorite")
                }

                // Large FAB (96dp) - A signature of M3 Expressive for prominent primary actions!
                LargeFloatingActionButton(
                    onClick = {},
                    containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                    contentColor = MaterialTheme.colorScheme.onTertiaryContainer
                ) {
                    Icon(
                        Icons.Rounded.Tune,
                        contentDescription = "Tune",
                        modifier = Modifier.size(36.dp)
                    )
                }
            }

            // Extended FAB (combines icon with expressive text label)
            ExtendedFloatingActionButton(
                onClick = {},
                icon = { Icon(Icons.Rounded.Send, contentDescription = null) },
                text = { Text("Extended Expressive FAB") },
                modifier = Modifier.align(Alignment.CenterHorizontally),
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
    }
}

@Composable
private fun SlidersShowcaseCard(
    continuousValue: Float,
    onContinuousChange: (Float) -> Unit,
    discreteValue: Float,
    onDiscreteChange: (Float) -> Unit
) {
    OutlinedCard(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Continuous Slider: ${continuousValue.roundToInt()}%",
                style = MaterialTheme.typography.titleSmall
            )
            Slider(
                value = continuousValue,
                onValueChange = onContinuousChange,
                valueRange = 0f..100f
            )

            Text(
                text = "Discrete Stepped Slider: Step ${discreteValue.roundToInt()} of 5",
                style = MaterialTheme.typography.titleSmall
            )
            Slider(
                value = discreteValue,
                onValueChange = onDiscreteChange,
                valueRange = 1f..5f,
                steps = 3 // 3 intermediate steps between 1 and 5 (total 5 positions: 1, 2, 3, 4, 5)
            )
        }
    }
}
