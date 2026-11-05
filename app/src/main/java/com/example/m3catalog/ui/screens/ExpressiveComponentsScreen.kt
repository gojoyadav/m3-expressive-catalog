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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Send
import androidx.compose.material.icons.rounded.Tune
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExtendedFloatingActionButton
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
 * EXPRESSIVE COMPONENTS GALLERY & CATEGORY PORTAL
 * Source of Truth: https://m3.material.io/components
 *
 * This screen provides both:
 * 1. The original classic components overview (Buttons, Cards, FABs, Sliders).
 * 2. An interactive category switcher to explore the full Material 3 & Expressive guideline hubs.
 * =========================================================================================
 */
@Composable
fun ExpressiveComponentsScreen(
    modifier: Modifier = Modifier
) {
    val hubCategories = listOf(
        "Classic Overview",
        "Actions & Buttons",
        "Selection & Inputs",
        "Text Fields & Search",
        "Containment & Sheets",
        "Navigation & App Bars",
        "Feedback & Pickers"
    )
    var selectedCategory by remember { mutableStateOf("Classic Overview") }

    Column(modifier = modifier.fillMaxSize()) {
        // Sticky Category Quick Filter Bar
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 6.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(hubCategories) { category ->
                val isSelected = selectedCategory == category
                FilterChip(
                    selected = isSelected,
                    onClick = { selectedCategory = category },
                    label = { Text(category) },
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

        // Body Content
        when (selectedCategory) {
            "Classic Overview" -> ClassicOverviewGallery()
            "Actions & Buttons" -> ActionsScreen()
            "Selection & Inputs" -> SelectionScreen()
            "Text Fields & Search" -> TextInputScreen()
            "Containment & Sheets" -> ContainmentScreen()
            "Navigation & App Bars" -> NavigationScreen()
            "Feedback & Pickers" -> CommunicationScreen()
            else -> ClassicOverviewGallery()
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun ClassicOverviewGallery(
    modifier: Modifier = Modifier
) {
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
            Spacer(modifier = Modifier.height(4.dp))
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

            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Filled Button
                Button(onClick = onIncrement) {
                    Icon(Icons.Rounded.Add, contentDescription = null, modifier = Modifier.size(18.dp))
                    Spacer(modifier = Modifier.size(6.dp))
                    Text("Filled")
                }

                // Filled Tonal
                FilledTonalButton(onClick = onIncrement) {
                    Text("Filled Tonal")
                }

                // Elevated Button
                ElevatedButton(onClick = onIncrement) {
                    Text("Elevated")
                }

                // Outlined Button
                OutlinedButton(onClick = onIncrement) {
                    Text("Outlined")
                }

                // Text Button
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
        // 1. Elevated Card
        ElevatedCard(
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.large
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

        // 2. Filled Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.large,
            colors = CardDefaults.cardColors(
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

        // 3. Outlined Card
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
                SmallFloatingActionButton(onClick = {}) {
                    Icon(Icons.Rounded.Edit, contentDescription = "Edit")
                }

                FloatingActionButton(onClick = {}) {
                    Icon(Icons.Rounded.Favorite, contentDescription = "Favorite")
                }

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
                steps = 3
            )
        }
    }
}
