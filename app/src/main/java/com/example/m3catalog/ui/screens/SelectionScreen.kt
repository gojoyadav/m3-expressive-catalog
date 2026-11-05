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
import androidx.compose.material.icons.rounded.CalendarMonth
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ElevatedAssistChip
import androidx.compose.material3.ElevatedFilterChip
import androidx.compose.material3.ElevatedSuggestionChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.InputChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.Slider
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TriStateCheckbox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.unit.dp
import com.example.m3catalog.ui.components.M3ComponentGuidelineCard
import kotlin.math.roundToInt

/**
 * =========================================================================================
 * SELECTION & INPUTS GALLERY
 * Source of Truth: https://m3.material.io/components/checkbox/overview
 *                  https://m3.material.io/components/radio-button/overview
 *                  https://m3.material.io/components/switch/overview
 *                  https://m3.material.io/components/chips/overview
 *                  https://m3.material.io/components/sliders/overview
 * =========================================================================================
 */
@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun SelectionScreen(
    modifier: Modifier = Modifier
) {
    // Checkbox states
    var childChecked1 by remember { mutableStateOf(true) }
    var childChecked2 by remember { mutableStateOf(false) }
    var childChecked3 by remember { mutableStateOf(true) }

    val parentState = remember(childChecked1, childChecked2, childChecked3) {
        if (childChecked1 && childChecked2 && childChecked3) {
            ToggleableState.On
        } else if (!childChecked1 && !childChecked2 && !childChecked3) {
            ToggleableState.Off
        } else {
            ToggleableState.Indeterminate
        }
    }

    // Radio state
    val radioOptions = listOf("Standard Delivery (Free)", "Express Delivery ($4.99)", "Same-Day Courier ($9.99)")
    var selectedDeliveryOption by remember { mutableStateOf(radioOptions[0]) }

    // Switch states
    var switch1 by remember { mutableStateOf(true) }
    var switchWithIcons by remember { mutableStateOf(true) }

    // Chip states
    var selectedFilter by remember { mutableStateOf("All") }
    var inputChipDismissed by remember { mutableStateOf(false) }

    // Sliders
    var continuousSliderVal by remember { mutableFloatStateOf(65f) }
    var steppedSliderVal by remember { mutableFloatStateOf(3f) }
    var sliderRange by remember { mutableStateOf(20f..80f) }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Selection & Toggles",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = "Interactive controls for multi-choice, single-choice, binary switches, chips, and range sliders.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        // ---------------------------------------------------------------------------------
        // 1. CHECKBOXES (STANDARD & TRI-STATE)
        // ---------------------------------------------------------------------------------
        item {
            M3ComponentGuidelineCard(
                title = "Checkboxes & Tri-State Indeterminate",
                tag = "M3 Core",
                category = "Selection Component",
                usageSummary = "Checkboxes let users select one or more items from a list, or turn an independent item on/off. Tri-state checkboxes represent parent-child relationships where some (but not all) children are selected.",
                doText = "Use TriStateCheckbox when a master toggle controls multiple child options.",
                dontText = "Do not use checkboxes for mutually exclusive options; use Radio Buttons instead.",
                anatomyNotes = "Box size 18x18dp, minimum touch target 48x48dp, corner radius 2dp.",
                codeSnippet = """
// Master Parent Tri-State Checkbox
TriStateCheckbox(
    state = parentState,
    onClick = {
        val next = parentState != ToggleableState.On
        child1 = next; child2 = next
    }
)

// Child Checkbox
Checkbox(
    checked = childChecked,
    onCheckedChange = { childChecked = it }
)
                """.trimIndent()
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    // Parent Tri-State
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                val next = parentState != ToggleableState.On
                                childChecked1 = next
                                childChecked2 = next
                                childChecked3 = next
                            }
                            .padding(vertical = 4.dp)
                    ) {
                        TriStateCheckbox(
                            state = parentState,
                            onClick = {
                                val next = parentState != ToggleableState.On
                                childChecked1 = next
                                childChecked2 = next
                                childChecked3 = next
                            }
                        )
                        Text(
                            text = "Select All Notifications (Parent)",
                            style = MaterialTheme.typography.titleSmall,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }

                    // Children
                    listOf(
                        "Email Digests" to childChecked1,
                        "SMS Alerts" to childChecked2,
                        "Push Notifications" to childChecked3
                    ).forEachIndexed { index, (label, checked) ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 24.dp)
                                .clickable {
                                    when (index) {
                                        0 -> childChecked1 = !childChecked1
                                        1 -> childChecked2 = !childChecked2
                                        2 -> childChecked3 = !childChecked3
                                    }
                                }
                                .padding(vertical = 2.dp)
                        ) {
                            Checkbox(
                                checked = checked,
                                onCheckedChange = { newChecked ->
                                    when (index) {
                                        0 -> childChecked1 = newChecked
                                        1 -> childChecked2 = newChecked
                                        2 -> childChecked3 = newChecked
                                    }
                                }
                            )
                            Text(text = label, style = MaterialTheme.typography.bodyMedium)
                        }
                    }
                }
            }
        }

        // ---------------------------------------------------------------------------------
        // 2. RADIO BUTTONS
        // ---------------------------------------------------------------------------------
        item {
            M3ComponentGuidelineCard(
                title = "Radio Buttons (Single Selection)",
                tag = "M3 Core",
                category = "Selection Component",
                usageSummary = "Radio buttons allow users to select exactly one option from a set of mutually exclusive choices. If all choices should be visible simultaneously, use Radio Buttons.",
                doText = "Always have one option selected by default so the field is never in an invalid empty state.",
                dontText = "Do not use radio buttons if more than one option can be selected simultaneously.",
                anatomyNotes = "Outer ring 20dp diameter, inner dot 10dp, minimum touch target 48x48dp.",
                codeSnippet = """
// Radio Button Group
radioOptions.forEach { option ->
    Row(
        modifier = Modifier.clickable { selectedOption = option },
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = (option == selectedOption),
            onClick = { selectedOption = option }
        )
        Text(option)
    }
}
                """.trimIndent()
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    radioOptions.forEach { option ->
                        val isSelected = option == selectedDeliveryOption
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { selectedDeliveryOption = option }
                                .padding(vertical = 4.dp)
                        ) {
                            RadioButton(
                                selected = isSelected,
                                onClick = { selectedDeliveryOption = option }
                            )
                            Text(
                                text = option,
                                style = MaterialTheme.typography.bodyMedium,
                                color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                }
            }
        }

        // ---------------------------------------------------------------------------------
        // 3. SWITCHES & ICON THUMBS
        // ---------------------------------------------------------------------------------
        item {
            M3ComponentGuidelineCard(
                title = "Switches & Icon Thumbs",
                tag = "M3 Expressive",
                category = "Selection Component",
                usageSummary = "Switches toggle the state of a single setting on or off. M3 switches feature enlarged thumbs and optional embedded thumb icons (such as checkmarks or crosses) to reinforce state accessibility.",
                doText = "Use switches for instantaneous binary activations (e.g. Dark Mode, Airplane Mode) that take effect immediately.",
                dontText = "Do not use switches if the user must tap a separate 'Save' button for the setting to apply.",
                anatomyNotes = "Track: 52x32dp (radius 16dp). Unchecked thumb: 16dp; Checked thumb: 24dp; With icon: 24dp.",
                codeSnippet = """
// Switch with Thumb Icon
Switch(
    checked = isChecked,
    onCheckedChange = { isChecked = it },
    thumbContent = {
        if (isChecked) {
            Icon(Icons.Rounded.Check, contentDescription = null, Modifier.size(16.dp))
        }
    }
)
                """.trimIndent()
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // 1. Standard
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("Standard", style = MaterialTheme.typography.labelSmall)
                        Switch(
                            checked = switch1,
                            onCheckedChange = { switch1 = it }
                        )
                    }

                    // 2. With Icon Thumb
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("With Icon", style = MaterialTheme.typography.labelSmall)
                        Switch(
                            checked = switchWithIcons,
                            onCheckedChange = { switchWithIcons = it },
                            thumbContent = {
                                if (switchWithIcons) {
                                    Icon(
                                        imageVector = Icons.Rounded.Check,
                                        contentDescription = null,
                                        modifier = Modifier.size(14.dp)
                                    )
                                } else {
                                    Icon(
                                        imageVector = Icons.Rounded.Close,
                                        contentDescription = null,
                                        modifier = Modifier.size(14.dp)
                                    )
                                }
                            }
                        )
                    }

                    // 3. Disabled Switch
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("Disabled", style = MaterialTheme.typography.labelSmall)
                        Switch(
                            checked = false,
                            onCheckedChange = {},
                            enabled = false
                        )
                    }
                }
            }
        }

        // ---------------------------------------------------------------------------------
        // 4. CHIPS (ASSIST, FILTER, INPUT, SUGGESTION)
        // ---------------------------------------------------------------------------------
        item {
            M3ComponentGuidelineCard(
                title = "Chips: Assist, Filter, Input & Suggestion",
                tag = "M3 Core",
                category = "Selection Component",
                usageSummary = "Chips help people enter information, make selections, filter content, or trigger actions. M3 defines 4 core chip types: Assist (smart actions), Filter (refining search/data), Input (discrete tags/people), and Suggestion (dynamic prompts).",
                doText = "Provide an explicit trailing remove cross on InputChips so users can delete individual tags.",
                dontText = "Do not use chips for primary call-to-actions; use standard Buttons instead.",
                anatomyNotes = "Container height 32dp, corner radius 8dp, minimum touch target 48x48dp.",
                codeSnippet = """
// 1. Assist Chip
AssistChip(
    onClick = {},
    label = { Text("Add to Calendar") },
    leadingIcon = { Icon(Icons.Rounded.CalendarMonth, contentDescription = null) }
)

// 2. Input Chip (Removable)
InputChip(
    selected = true,
    onClick = {},
    label = { Text("Alex Rivera") },
    trailingIcon = { Icon(Icons.Rounded.Close, contentDescription = "Remove") }
)
                """.trimIndent()
            ) {
                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // Assist Chip
                    AssistChip(
                        onClick = {},
                        label = { Text("Calendar") },
                        leadingIcon = {
                            Icon(Icons.Rounded.CalendarMonth, contentDescription = null, modifier = Modifier.size(18.dp))
                        }
                    )

                    // Elevated Assist Chip
                    ElevatedAssistChip(
                        onClick = {},
                        label = { Text("Share") },
                        leadingIcon = {
                            Icon(Icons.Rounded.Share, contentDescription = null, modifier = Modifier.size(18.dp))
                        }
                    )

                    // Filter Chip
                    val isFilterSelected = selectedFilter == "Design"
                    FilterChip(
                        selected = isFilterSelected,
                        onClick = { selectedFilter = if (isFilterSelected) "All" else "Design" },
                        label = { Text("Design Filter") },
                        leadingIcon = if (isFilterSelected) {
                            { Icon(Icons.Rounded.Check, contentDescription = null, modifier = Modifier.size(16.dp)) }
                        } else null
                    )

                    // Input Chip (Removable)
                    if (!inputChipDismissed) {
                        InputChip(
                            selected = true,
                            onClick = {},
                            label = { Text("John Doe") },
                            leadingIcon = {
                                Icon(Icons.Rounded.Person, contentDescription = null, modifier = Modifier.size(18.dp))
                            },
                            trailingIcon = {
                                Icon(
                                    imageVector = Icons.Rounded.Close,
                                    contentDescription = "Remove tag",
                                    modifier = Modifier
                                        .size(16.dp)
                                        .clickable { inputChipDismissed = true }
                                )
                            }
                        )
                    }

                    // Suggestion Chip
                    SuggestionChip(
                        onClick = {},
                        label = { Text("Near Me") },
                        icon = {
                            Icon(Icons.Rounded.LocationOn, contentDescription = null, modifier = Modifier.size(18.dp))
                        }
                    )
                }
            }
        }

        // ---------------------------------------------------------------------------------
        // 5. SLIDERS & RANGE SLIDER
        // ---------------------------------------------------------------------------------
        item {
            M3ComponentGuidelineCard(
                title = "Sliders & Dual-Thumb RangeSlider",
                tag = "M3 Core",
                category = "Selection Component",
                usageSummary = "Sliders reflect a range of values along a bar, from which users may select a single value or a range. Standard continuous sliders allow fine adjustments, stepped discrete sliders snap to specific increments, and RangeSliders select a min..max span.",
                doText = "Use RangeSlider when users need to filter between two bounds (e.g. Price: $20 - $80).",
                dontText = "Do not use sliders for sets with fewer than 3 values; use segmented buttons or radio buttons.",
                anatomyNotes = "Track height 4dp (or 16dp pill in M3 Expressive), thumb diameter 20dp.",
                codeSnippet = """
// Dual-Thumb Range Slider
RangeSlider(
    value = sliderRange,
    onValueChange = { sliderRange = it },
    valueRange = 0f..100f
)
                """.trimIndent()
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // Continuous
                    Column {
                        Text(
                            text = "Continuous Slider: ${continuousSliderVal.roundToInt()}%",
                            style = MaterialTheme.typography.labelMedium
                        )
                        Slider(
                            value = continuousSliderVal,
                            onValueChange = { continuousSliderVal = it },
                            valueRange = 0f..100f
                        )
                    }

                    // Discrete
                    Column {
                        Text(
                            text = "Discrete Stepped Slider: Step ${steppedSliderVal.roundToInt()} of 5",
                            style = MaterialTheme.typography.labelMedium
                        )
                        Slider(
                            value = steppedSliderVal,
                            onValueChange = { steppedSliderVal = it },
                            valueRange = 1f..5f,
                            steps = 3
                        )
                    }

                    // RangeSlider
                    Column {
                        Text(
                            text = "Dual-Thumb Range: ${sliderRange.start.roundToInt()} - ${sliderRange.endInclusive.roundToInt()}",
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.primary
                        )
                        RangeSlider(
                            value = sliderRange,
                            onValueChange = { sliderRange = it },
                            valueRange = 0f..100f
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}
