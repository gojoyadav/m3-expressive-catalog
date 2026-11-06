package com.example.m3catalog.ui.screens

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.HelpOutline
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.Layers
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.PlainTooltip
import androidx.compose.material3.RichTooltip
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.TooltipDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.m3catalog.ui.components.M3ComponentGuidelineCard
import kotlinx.coroutines.launch

/**
 * =========================================================================================
 * CONTAINMENT & SHEETS GALLERY
 * Source of Truth: https://m3.material.io/components/cards/overview
 *                  https://m3.material.io/components/dialogs/overview
 *                  https://m3.material.io/components/bottom-sheets/overview
 *                  https://m3.material.io/components/menus/overview
 *                  https://m3.material.io/components/tooltips/overview
 *                  https://m3.material.io/components/lists/overview
 *                  https://m3.material.io/components/divider/overview
 * =========================================================================================
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContainmentScreen(
    modifier: Modifier = Modifier
) {
    // Dialog state
    var showAlertDialog by remember { mutableStateOf(false) }

    // Bottom Sheet state
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }

    // Dropdown Menu state
    var isMenuExpanded by remember { mutableStateOf(false) }
    var menuSelection by remember { mutableStateOf("None") }

    // List switch state
    var listSwitchState by remember { mutableStateOf(true) }

    // Tooltip states
    val plainTooltipState = rememberTooltipState()
    val richTooltipState = rememberTooltipState()

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Containment & Sheets",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = "Surface cards, modal dialogs, bottom sheets, menus, tooltips, and list items.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        // ---------------------------------------------------------------------------------
        // 1. CARDS SUITE
        // ---------------------------------------------------------------------------------
        item {
            M3ComponentGuidelineCard(
                title = "Cards (Elevated, Filled & Outlined)",
                tag = "M3 Core",
                category = "Containment Component",
                usageSummary = "Cards contain content and actions about a single subject. M3 cards use generous 24dp corner curvature. Elevated cards provide soft tonal lift; Filled cards group items on default surfaces; Outlined cards provide clean 1dp boundaries.",
                doText = "Make entire card surface clickable if the card represents a single actionable item.",
                dontText = "Do not nest multiple filled cards within each other with matching container colors.",
                anatomyNotes = "Default shape RoundedCornerShape(24.dp), content padding 16dp.",
                codeSnippet = """
// Clickable Elevated Card
ElevatedCard(
    onClick = { /* navigate */ },
    shape = MaterialTheme.shapes.large, // 24dp
    modifier = Modifier.fillMaxWidth()
) {
    Column(Modifier.padding(16.dp)) {
        Text("Card Header", style = MaterialTheme.typography.titleMedium)
        Text("Subhead text", style = MaterialTheme.typography.bodyMedium)
    }
}
                """.trimIndent()
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    ElevatedCard(
                        onClick = {},
                        shape = MaterialTheme.shapes.large,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text("Interactive Elevated Card (24dp)", style = MaterialTheme.typography.titleMedium)
                            Text("Soft tonal lift; tap to see native ripple animation.", style = MaterialTheme.typography.bodySmall)
                        }
                    }

                    Card(
                        onClick = {},
                        shape = MaterialTheme.shapes.large,
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerHighest),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text("Filled Card (Surface Container Highest)", style = MaterialTheme.typography.titleMedium)
                            Text("Strong grouping contrast without drop shadows.", style = MaterialTheme.typography.bodySmall)
                        }
                    }

                    OutlinedCard(
                        onClick = {},
                        shape = MaterialTheme.shapes.large,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text("Outlined Card (1dp Minimalist)", style = MaterialTheme.typography.titleMedium)
                            Text("Crisp boundary for high-density content layouts.", style = MaterialTheme.typography.bodySmall)
                        }
                    }
                }
            }
        }

        // ---------------------------------------------------------------------------------
        // 2. DIALOGS
        // ---------------------------------------------------------------------------------
        item {
            M3ComponentGuidelineCard(
                title = "Alert & Confirmation Dialogs",
                tag = "M3 Core",
                category = "Containment Component",
                usageSummary = "Dialogs provide urgent information, details, or actions requiring immediate user interaction. An Alert Dialog features an icon, title, message body, confirm button, and optional dismiss button.",
                doText = "Keep dialog titles brief and focused on the immediate decision (e.g., 'Delete Project?').",
                dontText = "Do not use dialogs for mundane feedback; use Snackbars or Toasts instead.",
                anatomyNotes = "Corner radius 28dp (surfaceContainerHigh), title font headlineSmall (24sp).",
                codeSnippet = """
AlertDialog(
    onDismissRequest = { showDialog = false },
    icon = { Icon(Icons.Rounded.Warning, contentDescription = null) },
    title = { Text("Discard Draft?") },
    text = { Text("Any unsaved changes will be permanently lost.") },
    confirmButton = {
        Button(onClick = { showDialog = false }) { Text("Discard") }
    },
    dismissButton = {
        TextButton(onClick = { showDialog = false }) { Text("Cancel") }
    }
)
                """.trimIndent()
            ) {
                Button(onClick = { showAlertDialog = true }) {
                    Icon(Icons.Rounded.Warning, contentDescription = null, modifier = Modifier.size(18.dp))
                    Spacer(Modifier.width(8.dp))
                    Text("Trigger Alert Dialog")
                }

                if (showAlertDialog) {
                    AlertDialog(
                        onDismissRequest = { showAlertDialog = false },
                        icon = {
                            Icon(
                                imageVector = Icons.Rounded.Warning,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(28.dp)
                            )
                        },
                        title = {
                            Text("Discard Local Changes?")
                        },
                        text = {
                            Text("Are you sure you want to revert this component? This action cannot be undone.")
                        },
                        confirmButton = {
                            Button(
                                onClick = { showAlertDialog = false }
                            ) {
                                Text("Discard")
                            }
                        },
                        dismissButton = {
                            TextButton(
                                onClick = { showAlertDialog = false }
                            ) {
                                Text("Keep Editing")
                            }
                        }
                    )
                }
            }
        }

        // ---------------------------------------------------------------------------------
        // 3. MODAL BOTTOM SHEET
        // ---------------------------------------------------------------------------------
        item {
            M3ComponentGuidelineCard(
                title = "Modal Bottom Sheet",
                tag = "M3 Core",
                category = "Containment Component",
                usageSummary = "Modal bottom sheets present a set of options or supplementary workflows anchored to the bottom of the screen. They include an accessible drag handle and dark scrim overlay.",
                doText = "Include a drag handle at the top of the sheet to indicate vertical swipe gesture support.",
                dontText = "Do not place long multi-page wizards inside bottom sheets; navigate to a full screen instead.",
                anatomyNotes = "Top corner radius 28dp, drag handle 32x4dp (radius 2dp), surfaceContainerLow.",
                codeSnippet = """
ModalBottomSheet(
    onDismissRequest = { showSheet = false },
    sheetState = sheetState
) {
    Column(Modifier.padding(24.dp)) {
        Text("Sheet Content", style = MaterialTheme.typography.titleLarge)
        // items
    }
}
                """.trimIndent()
            ) {
                FilledTonalButton(onClick = { showBottomSheet = true }) {
                    Icon(Icons.Rounded.Layers, contentDescription = null, modifier = Modifier.size(18.dp))
                    Spacer(Modifier.width(8.dp))
                    Text("Open Modal Bottom Sheet")
                }

                if (showBottomSheet) {
                    ModalBottomSheet(
                        onDismissRequest = { showBottomSheet = false },
                        sheetState = sheetState
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(24.dp),
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            Text(
                                text = "M3 Modal Bottom Sheet",
                                style = MaterialTheme.typography.titleLarge
                            )
                            Text(
                                text = "Features spring-driven swipe-to-dismiss physics, standard top drag handle, and surface container elevation.",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.End
                            ) {
                                Button(
                                    onClick = {
                                        scope.launch { sheetState.hide() }.invokeOnCompletion {
                                            if (!sheetState.isVisible) showBottomSheet = false
                                        }
                                    }
                                ) {
                                    Text("Dismiss Sheet")
                                }
                            }
                            Spacer(modifier = Modifier.height(16.dp))
                        }
                    }
                }
            }
        }

        // ---------------------------------------------------------------------------------
        // 4. DROPDOWN MENUS
        // ---------------------------------------------------------------------------------
        item {
            M3ComponentGuidelineCard(
                title = "Dropdown Menus",
                tag = "M3 Core",
                category = "Containment / Actions",
                usageSummary = "Menus display a list of choices on a temporary surface that appears when users interact with a button or contextual trigger.",
                doText = "Keep menu action items concise and include leading icons for quick scanning.",
                dontText = "Do not use menus for primary navigation destinations; use Navigation Bars or Drawers.",
                anatomyNotes = "Menu container shape RoundedCornerShape(12dp), min width 112dp, max width 280dp.",
                codeSnippet = """
Box {
    IconButton(onClick = { expanded = true }) {
        Icon(Icons.Rounded.MoreVert, contentDescription = "Menu")
    }
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false }
    ) {
        DropdownMenuItem(
            text = { Text("Edit") },
            onClick = { /* action */ },
            leadingIcon = { Icon(Icons.Rounded.Edit, contentDescription = null) }
        )
    }
}
                """.trimIndent()
            ) {
                Box {
                    OutlinedButton(onClick = { isMenuExpanded = true }) {
                        Text("Open Options Menu (Selected: $menuSelection)")
                        Spacer(Modifier.width(6.dp))
                        Icon(Icons.Rounded.MoreVert, contentDescription = null)
                    }

                    DropdownMenu(
                        expanded = isMenuExpanded,
                        onDismissRequest = { isMenuExpanded = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text("Edit Properties") },
                            onClick = {
                                menuSelection = "Edit"
                                isMenuExpanded = false
                            },
                            leadingIcon = { Icon(Icons.Rounded.Edit, contentDescription = null) }
                        )
                        DropdownMenuItem(
                            text = { Text("Share Component") },
                            onClick = {
                                menuSelection = "Share"
                                isMenuExpanded = false
                            },
                            leadingIcon = { Icon(Icons.Rounded.Share, contentDescription = null) }
                        )
                        HorizontalDivider()
                        DropdownMenuItem(
                            text = { Text("Delete Item", color = MaterialTheme.colorScheme.error) },
                            onClick = {
                                menuSelection = "Delete"
                                isMenuExpanded = false
                            },
                            leadingIcon = { Icon(Icons.Rounded.Delete, contentDescription = null, tint = MaterialTheme.colorScheme.error) }
                        )
                    }
                }
            }
        }

        // ---------------------------------------------------------------------------------
        // 5. TOOLTIPS (PLAIN & RICH)
        // ---------------------------------------------------------------------------------
        item {
            M3ComponentGuidelineCard(
                title = "Tooltips (Plain & Rich)",
                tag = "M3 Core",
                category = "Containment / Communication",
                usageSummary = "Tooltips provide brief informational context when users hover over or long-press an element. Plain Tooltips show lightweight labels; Rich Tooltips include title, body, and action buttons.",
                doText = "Use Plain Tooltips to explain icon-only buttons for users who cannot guess the symbol.",
                dontText = "Do not hide critical information exclusively behind tooltips on touch devices.",
                anatomyNotes = "Plain: 4dp corner radius, inverseSurface tone. Rich: 12dp corner radius, surfaceContainerHigh.",
                codeSnippet = """
// Plain Tooltip
TooltipBox(
    positionProvider = TooltipDefaults.rememberPlainTooltipPositionProvider(),
    tooltip = { PlainTooltip { Text("Add to bookmarks") } },
    state = rememberTooltipState()
) {
    IconButton(onClick = {}) { Icon(Icons.Rounded.Bookmark, contentDescription = null) }
}
                """.trimIndent()
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Plain Tooltip
                    TooltipBox(
                        positionProvider = TooltipDefaults.rememberPlainTooltipPositionProvider(),
                        tooltip = {
                            PlainTooltip {
                                Text("Long-press: Help information")
                            }
                        },
                        state = plainTooltipState
                    ) {
                        IconButton(onClick = {}) {
                            Icon(Icons.Rounded.HelpOutline, contentDescription = "Help")
                        }
                    }

                    // Rich Tooltip
                    TooltipBox(
                        positionProvider = TooltipDefaults.rememberRichTooltipPositionProvider(),
                        tooltip = {
                            RichTooltip(
                                title = { Text("M3 Expressive Tooltip") },
                                action = {
                                    TextButton(onClick = {}) { Text("Learn More") }
                                }
                            ) {
                                Text("Rich tooltips display interactive subheads and contextual action triggers.")
                            }
                        },
                        state = richTooltipState
                    ) {
                        FilledTonalButton(onClick = {}) {
                            Icon(Icons.Rounded.Info, contentDescription = null, modifier = Modifier.size(16.dp))
                            Spacer(Modifier.width(6.dp))
                            Text("Hover/Press for Rich Tooltip")
                        }
                    }
                }
            }
        }

        // ---------------------------------------------------------------------------------
        // 6. LIST ITEMS & DIVIDERS
        // ---------------------------------------------------------------------------------
        item {
            M3ComponentGuidelineCard(
                title = "Lists (1-3 Lines) & Dividers",
                tag = "M3 Core",
                category = "Containment Component",
                usageSummary = "Lists are continuous vertical indexes of text and images. Material 3 `ListItem` natively supports 1, 2, or 3-line configurations with leading media (icons, avatars) and trailing interactive widgets (switches, checkboxes).",
                doText = "Use HorizontalDivider with subtle outlineVariant color to visually separate distinct sections.",
                dontText = "Do not place dividers between every single list item if spacing alone creates clear grouping.",
                anatomyNotes = "1-line height 56dp, 2-line height 72dp, 3-line height 88dp.",
                codeSnippet = """
ListItem(
    headlineContent = { Text("Notifications") },
    supportingContent = { Text("Receive daily design updates") },
    leadingContent = { Icon(Icons.Rounded.Notifications, contentDescription = null) },
    trailingContent = { Switch(checked = checked, onCheckedChange = { checked = it }) }
)
                """.trimIndent()
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    // 1-line item
                    ListItem(
                        headlineContent = { Text("Account Profile") },
                        leadingContent = { Icon(Icons.Rounded.Person, contentDescription = null) },
                        trailingContent = { Icon(Icons.Rounded.Edit, contentDescription = null) },
                        colors = ListItemDefaults.colors(containerColor = MaterialTheme.colorScheme.surfaceContainerLowest)
                    )
                    HorizontalDivider()

                    // 2-line item with switch
                    ListItem(
                        headlineContent = { Text("Haptic Feedback") },
                        supportingContent = { Text("Vibrate slightly on tap interactions") },
                        leadingContent = { Icon(Icons.Rounded.Check, contentDescription = null) },
                        trailingContent = {
                            Switch(
                                checked = listSwitchState,
                                onCheckedChange = { listSwitchState = it }
                            )
                        },
                        colors = ListItemDefaults.colors(containerColor = MaterialTheme.colorScheme.surfaceContainerLowest)
                    )
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}
