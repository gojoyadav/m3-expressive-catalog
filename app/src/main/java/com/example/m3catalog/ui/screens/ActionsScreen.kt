package com.example.m3catalog.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.ArrowDropDown
import androidx.compose.material.icons.rounded.Bookmark
import androidx.compose.material.icons.rounded.BookmarkBorder
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.rounded.Send
import androidx.compose.material.icons.rounded.Tune
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.FilledIconToggleButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MultiChoiceSegmentedButtonRow
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.m3catalog.ui.components.M3ComponentGuidelineCard

/**
 * =========================================================================================
 * ACTIONS & BUTTONS GALLERY
 * Source of Truth: https://m3.material.io/components/buttons/overview
 *                  https://m3.material.io/components/floating-action-button/overview
 *                  https://m3.material.io/components/segmented-buttons/overview
 *                  https://m3.material.io/components/icon-buttons/overview
 *
 * Covers:
 * 1. 5 Button Variants (Filled, Tonal, Elevated, Outlined, Text)
 * 2. M3 Expressive 5-Tier Size Hierarchy (Extra Small to Extra Large)
 * 3. M3 Expressive Split Button Layout
 * 4. Single-Choice & Multi-Choice Segmented Buttons
 * 5. Full Icon Button Suite & Toggle States
 * 6. FAB Hierarchy (Small, Standard, Large 96dp, Extended)
 * =========================================================================================
 */
@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun ActionsScreen(
    modifier: Modifier = Modifier
) {
    // Interactive states
    var selectedSegmentIndex by remember { mutableIntStateOf(0) }
    val multiSelectedSegments = remember { mutableStateListOf(0) }
    var isFavoriteToggled by remember { mutableStateOf(false) }
    var isBookmarkToggled by remember { mutableStateOf(true) }
    var selectedSize by remember { mutableStateOf("Medium") }
    var tapCounter by remember { mutableIntStateOf(0) }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Actions & Buttons",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = "Comprehensive action hierarchy from subtle links to high-impact primary triggers.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        // ---------------------------------------------------------------------------------
        // 1. COMMON BUTTONS (5 VARIANTS)
        // ---------------------------------------------------------------------------------
        item {
            M3ComponentGuidelineCard(
                title = "Common Buttons (5 Variants)",
                tag = "M3 Core",
                category = "Action Component",
                usageSummary = "Buttons initiate actions. Use Filled for the 1 dominant primary action on screen, Filled Tonal for medium-high emphasis, Elevated on colored/surface cards, Outlined for secondary actions, and Text for subtle actions in cards/dialogs.",
                doText = "Keep one primary filled button per visual area to guide the user's attention clearly.",
                dontText = "Do not place multiple filled primary buttons side-by-side with equal visual weight.",
                anatomyNotes = "Default height 40dp, minimum touch target 48x48dp, corner radius Full/Pill (CircleShape).",
                codeSnippet = """
// 1. Primary Filled Button
Button(onClick = { /* action */ }) {
    Icon(Icons.Rounded.Add, contentDescription = null)
    Spacer(Modifier.width(8.dp))
    Text("Filled Button")
}

// 2. Filled Tonal (Secondary Container)
FilledTonalButton(onClick = { /* action */ }) {
    Text("Filled Tonal")
}

// 3. Outlined Button
OutlinedButton(onClick = { /* action */ }) {
    Text("Outlined")
}
                """.trimIndent()
            ) {
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Button(onClick = { tapCounter++ }) {
                        Icon(Icons.Rounded.Add, contentDescription = null, modifier = Modifier.size(18.dp))
                        Spacer(Modifier.width(6.dp))
                        Text("Filled")
                    }
                    FilledTonalButton(onClick = { tapCounter++ }) {
                        Text("Filled Tonal")
                    }
                    ElevatedButton(onClick = { tapCounter++ }) {
                        Text("Elevated")
                    }
                    OutlinedButton(onClick = { tapCounter++ }) {
                        Text("Outlined")
                    }
                    TextButton(onClick = { tapCounter++ }) {
                        Text("Text")
                    }
                }
            }
        }

        // ---------------------------------------------------------------------------------
        // 2. M3 EXPRESSIVE 5-TIER BUTTON SIZE HIERARCHY
        // ---------------------------------------------------------------------------------
        item {
            M3ComponentGuidelineCard(
                title = "M3 Expressive Button Sizes (XS to XL)",
                tag = "M3 Expressive",
                category = "Action Component",
                usageSummary = "M3 Expressive introduces an expanded 5-level size hierarchy: Extra Small (32dp), Small (36dp), Medium (40dp baseline), Large (48dp), and Extra Large (56dp). This gives editorial freedom to match button density with visual context.",
                doText = "Use Extra Large (56dp) on landing hero sections and Extra Small (32dp) inside tight table/list cells.",
                dontText = "Do not mix 3 different button heights in the same linear row.",
                anatomyNotes = "XS: 32dp height (padding 12x4dp), S: 36dp (16x6dp), M: 40dp (24x8dp), L: 48dp (28x12dp), XL: 56dp (32x16dp).",
                codeSnippet = """
// Extra Small (32dp)
Button(
    onClick = {},
    contentPadding = PaddingValues(horizontal = 12.dp, vertical = 4.dp),
    modifier = Modifier.height(32.dp)
) { Text("XS", style = MaterialTheme.typography.labelSmall) }

// Extra Large (56dp)
Button(
    onClick = {},
    contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp),
    modifier = Modifier.height(56.dp)
) { Text("Extra Large", style = MaterialTheme.typography.titleMedium) }
                """.trimIndent()
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        itemVerticalAlignment = Alignment.CenterVertically
                    ) {
                        // Extra Small (32dp)
                        Button(
                            onClick = { selectedSize = "XS" },
                            contentPadding = PaddingValues(horizontal = 12.dp, vertical = 2.dp),
                            modifier = Modifier.height(32.dp)
                        ) {
                            Text("XS (32dp)", style = MaterialTheme.typography.labelSmall)
                        }

                        // Small (36dp)
                        Button(
                            onClick = { selectedSize = "S" },
                            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 6.dp),
                            modifier = Modifier.height(36.dp)
                        ) {
                            Text("Small (36dp)", style = MaterialTheme.typography.labelMedium)
                        }

                        // Medium (40dp Standard)
                        Button(
                            onClick = { selectedSize = "M" },
                            modifier = Modifier.height(40.dp)
                        ) {
                            Text("Medium (40dp)")
                        }

                        // Large (48dp)
                        Button(
                            onClick = { selectedSize = "L" },
                            contentPadding = PaddingValues(horizontal = 24.dp, vertical = 10.dp),
                            modifier = Modifier.height(48.dp)
                        ) {
                            Text("Large (48dp)", style = MaterialTheme.typography.titleSmall)
                        }

                        // Extra Large (56dp Hero)
                        Button(
                            onClick = { selectedSize = "XL" },
                            contentPadding = PaddingValues(horizontal = 28.dp, vertical = 14.dp),
                            modifier = Modifier.height(56.dp)
                        ) {
                            Text("Extra Large (56dp)", style = MaterialTheme.typography.titleMedium)
                        }
                    }
                }
            }
        }

        // ---------------------------------------------------------------------------------
        // 3. M3 EXPRESSIVE SPLIT BUTTON
        // ---------------------------------------------------------------------------------
        item {
            M3ComponentGuidelineCard(
                title = "M3 Expressive Split Button",
                tag = "M3 Expressive",
                category = "Action Component",
                usageSummary = "The Split Button pairs a primary action with a connected trailing dropdown trigger. It lets users trigger a default action with 1 tap, while providing instant access to contextual sub-actions without leaving the screen.",
                doText = "Use when an action has multiple closely related variations (e.g. 'Send' vs 'Schedule Send').",
                dontText = "Do not use when secondary actions are completely unrelated to the primary button.",
                anatomyNotes = "Primary container shape: RoundedCornerShape(topStart = 24.dp, bottomStart = 24.dp, topEnd = 4.dp, bottomEnd = 4.dp); Trailing container shape: RoundedCornerShape(topStart = 4.dp, bottomStart = 4.dp, topEnd = 24.dp, bottomEnd = 24.dp); Inter-gap 2dp.",
                codeSnippet = """
// Idiomatic M3 Expressive Split Button
Row(verticalAlignment = Alignment.CenterVertically) {
    Button(
        onClick = { /* primary action */ },
        shape = RoundedCornerShape(topStart = 24.dp, bottomStart = 24.dp, topEnd = 4.dp, bottomEnd = 4.dp)
    ) { Text("Send Message") }
    
    Spacer(Modifier.width(2.dp))
    
    Button(
        onClick = { /* open dropdown menu */ },
        shape = RoundedCornerShape(topStart = 4.dp, bottomStart = 4.dp, topEnd = 24.dp, bottomEnd = 24.dp),
        contentPadding = PaddingValues(horizontal = 8.dp)
    ) { Icon(Icons.Rounded.ArrowDropDown, contentDescription = "More") }
}
                """.trimIndent()
            ) {
                var splitActionFeedback by remember { mutableStateOf("Default Action: Send Now") }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Button(
                            onClick = { splitActionFeedback = "Executed: Primary Action!" },
                            shape = RoundedCornerShape(topStart = 24.dp, bottomStart = 24.dp, topEnd = 4.dp, bottomEnd = 4.dp),
                            modifier = Modifier.height(48.dp)
                        ) {
                            Icon(Icons.Rounded.Send, contentDescription = null, modifier = Modifier.size(18.dp))
                            Spacer(Modifier.width(8.dp))
                            Text("Send Message")
                        }

                        Spacer(modifier = Modifier.width(2.dp))

                        Button(
                            onClick = { splitActionFeedback = "Opened: Options Dropdown!" },
                            shape = RoundedCornerShape(topStart = 4.dp, bottomStart = 4.dp, topEnd = 24.dp, bottomEnd = 24.dp),
                            contentPadding = PaddingValues(horizontal = 10.dp),
                            modifier = Modifier.height(48.dp)
                        ) {
                            Icon(Icons.Rounded.ArrowDropDown, contentDescription = "More actions")
                        }
                    }

                    Text(
                        text = splitActionFeedback,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }

        // ---------------------------------------------------------------------------------
        // 4. SEGMENTED BUTTONS
        // ---------------------------------------------------------------------------------
        item {
            M3ComponentGuidelineCard(
                title = "Segmented Buttons",
                tag = "M3 Core",
                category = "Selection / Action",
                usageSummary = "Segmented buttons help users select options, switch views, or sort elements. Available in Single-Select (mutually exclusive) and Multi-Select (multiple simultaneous choices).",
                doText = "Limit to 2 to 5 segments. Use clear concise labels or recognizable icons.",
                dontText = "Do not wrap segmented buttons into multiple lines. On narrow screens, consider tabs or chips.",
                anatomyNotes = "Default container height 40dp, border outline 1dp, active indicator fills with secondaryContainer.",
                codeSnippet = """
// Single-Select Segmented Button Row
val options = listOf("Day", "Week", "Month")
SingleChoiceSegmentedButtonRow {
    options.forEachIndexed { index, label ->
        SegmentedButton(
            shape = SegmentedButtonDefaults.itemShape(index = index, count = options.size),
            onClick = { selectedIndex = index },
            selected = index == selectedIndex
        ) { Text(label) }
    }
}
                """.trimIndent()
            ) {
                val singleOptions = listOf("List", "Grid", "Map")
                val multiOptions = listOf("Wi-Fi", "Bluetooth", "NFC")

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Single-Choice (Selected: ${singleOptions[selectedSegmentIndex]})",
                        style = MaterialTheme.typography.labelMedium
                    )
                    SingleChoiceSegmentedButtonRow(modifier = Modifier.fillMaxWidth()) {
                        singleOptions.forEachIndexed { index, label ->
                            SegmentedButton(
                                shape = SegmentedButtonDefaults.itemShape(index = index, count = singleOptions.size),
                                onClick = { selectedSegmentIndex = index },
                                selected = index == selectedSegmentIndex,
                                icon = {
                                    SegmentedButtonDefaults.Icon(active = index == selectedSegmentIndex) {
                                        Icon(Icons.Rounded.Check, contentDescription = null, modifier = Modifier.size(16.dp))
                                    }
                                }
                            ) {
                                Text(label)
                            }
                        }
                    }

                    Text(
                        text = "Multi-Choice Toggles",
                        style = MaterialTheme.typography.labelMedium
                    )
                    MultiChoiceSegmentedButtonRow(modifier = Modifier.fillMaxWidth()) {
                        multiOptions.forEachIndexed { index, label ->
                            val isChecked = multiSelectedSegments.contains(index)
                            SegmentedButton(
                                shape = SegmentedButtonDefaults.itemShape(index = index, count = multiOptions.size),
                                checked = isChecked,
                                onCheckedChange = {
                                    if (isChecked) multiSelectedSegments.remove(index) else multiSelectedSegments.add(index)
                                },
                                icon = {
                                    SegmentedButtonDefaults.Icon(active = isChecked) {
                                        Icon(Icons.Rounded.Check, contentDescription = null, modifier = Modifier.size(16.dp))
                                    }
                                }
                            ) {
                                Text(label)
                            }
                        }
                    }
                }
            }
        }

        // ---------------------------------------------------------------------------------
        // 5. ICON BUTTONS & TOGGLES
        // ---------------------------------------------------------------------------------
        item {
            M3ComponentGuidelineCard(
                title = "Icon Buttons & Toggle States",
                tag = "M3 Core",
                category = "Action Component",
                usageSummary = "Icon buttons allow users to take actions and make choices with a single tap. Available in 4 container styles (Standard, Filled, Filled Tonal, Outlined) plus stateful toggle variants for favorites, bookmarks, and pins.",
                doText = "Always supply a descriptive `contentDescription` for screen readers and accessibility.",
                dontText = "Do not use ambiguous icons without a tooltip or clear surrounding context.",
                anatomyNotes = "Standard touch target 48x48dp, icon size 24dp, container shape CircleShape.",
                codeSnippet = """
// 1. Standard Icon Button
IconButton(onClick = {}) {
    Icon(Icons.Rounded.Favorite, contentDescription = "Favorite")
}

// 2. Filled Icon Toggle Button
FilledIconToggleButton(
    checked = isFavorite,
    onCheckedChange = { isFavorite = it }
) {
    Icon(
        imageVector = if (isFavorite) Icons.Rounded.Favorite else Icons.Rounded.FavoriteBorder,
        contentDescription = null
    )
}
                """.trimIndent()
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Standard
                    IconButton(onClick = {}) {
                        Icon(Icons.Rounded.Edit, contentDescription = "Edit")
                    }

                    // Filled
                    FilledIconButton(onClick = {}) {
                        Icon(Icons.Rounded.Tune, contentDescription = "Settings")
                    }

                    // Filled Tonal
                    FilledTonalIconButton(onClick = {}) {
                        Icon(Icons.Rounded.Bookmark, contentDescription = "Bookmark")
                    }

                    // Outlined
                    OutlinedIconButton(onClick = {}) {
                        Icon(Icons.Rounded.Send, contentDescription = "Share")
                    }

                    // Filled Toggle Button
                    FilledIconToggleButton(
                        checked = isFavoriteToggled,
                        onCheckedChange = { isFavoriteToggled = it }
                    ) {
                        Icon(
                            imageVector = if (isFavoriteToggled) Icons.Rounded.Favorite else Icons.Rounded.FavoriteBorder,
                            contentDescription = "Toggle favorite"
                        )
                    }

                    // Standard Toggle Button
                    IconToggleButton(
                        checked = isBookmarkToggled,
                        onCheckedChange = { isBookmarkToggled = it }
                    ) {
                        Icon(
                            imageVector = if (isBookmarkToggled) Icons.Rounded.Bookmark else Icons.Rounded.BookmarkBorder,
                            contentDescription = "Toggle bookmark",
                            tint = if (isBookmarkToggled) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }

        // ---------------------------------------------------------------------------------
        // 6. FLOATING ACTION BUTTONS (FABs)
        // ---------------------------------------------------------------------------------
        item {
            M3ComponentGuidelineCard(
                title = "Floating Action Buttons (FABs)",
                tag = "M3 Expressive",
                category = "Action Component",
                usageSummary = "FABs represent the most important screen action. M3 features 4 sizes: Small (40dp), Standard (56dp), Large (96dp - M3 Expressive signature), and Extended (which collapses to standard on scroll).",
                doText = "Reserve Large FAB (96dp) for the single centerpiece action on prominent overview screens.",
                dontText = "Never place more than one FAB on the same screen view.",
                anatomyNotes = "Small: 40dp (12dp radius), Standard: 56dp (16dp radius), Large: 96dp (28dp radius), Extended: 56dp height (Full pill radius).",
                codeSnippet = """
// Large FAB (96dp - M3 Expressive Signature)
LargeFloatingActionButton(
    onClick = {},
    containerColor = MaterialTheme.colorScheme.tertiaryContainer,
    contentColor = MaterialTheme.colorScheme.onTertiaryContainer
) {
    Icon(Icons.Rounded.Tune, contentDescription = "Action", modifier = Modifier.size(36.dp))
}

// Extended FAB with Text & Icon
ExtendedFloatingActionButton(
    onClick = {},
    icon = { Icon(Icons.Rounded.Add, contentDescription = null) },
    text = { Text("Compose") }
)
                """.trimIndent()
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        SmallFloatingActionButton(onClick = {}) {
                            Icon(Icons.Rounded.Edit, contentDescription = "Small FAB")
                        }

                        FloatingActionButton(onClick = {}) {
                            Icon(Icons.Rounded.Favorite, contentDescription = "Standard FAB")
                        }

                        LargeFloatingActionButton(
                            onClick = {},
                            containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                            contentColor = MaterialTheme.colorScheme.onTertiaryContainer
                        ) {
                            Icon(Icons.Rounded.Tune, contentDescription = "Large FAB", modifier = Modifier.size(36.dp))
                        }
                    }

                    ExtendedFloatingActionButton(
                        onClick = {},
                        icon = { Icon(Icons.Rounded.Send, contentDescription = null) },
                        text = { Text("Extended Expressive FAB") },
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}
