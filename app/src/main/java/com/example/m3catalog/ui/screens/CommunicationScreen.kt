package com.example.m3catalog.ui.screens

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.icons.rounded.AccessTime
import androidx.compose.material.icons.rounded.CalendarMonth
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimeInput
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.m3catalog.ui.components.M3ComponentGuidelineCard
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * =========================================================================================
 * COMMUNICATION & FEEDBACK GALLERY
 * Source of Truth: https://m3.material.io/components/badges/overview
 *                  https://m3.material.io/components/progress-indicators/overview
 *                  https://m3.material.io/components/snackbar/overview
 *                  https://m3.material.io/components/date-pickers/overview
 *                  https://m3.material.io/components/time-pickers/overview
 * =========================================================================================
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommunicationScreen(
    modifier: Modifier = Modifier
) {
    // Snackbar state
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    var snackbarResultText by remember { mutableStateOf("") }

    // Date Picker state
    val datePickerState = rememberDatePickerState()
    var showDatePickerDialog by remember { mutableStateOf(false) }

    // Time Picker state
    val timePickerState = rememberTimePickerState(initialHour = 10, initialMinute = 30, is24Hour = false)
    var isTimeInputMode by remember { mutableStateOf(false) }

    // Progress
    var progressVal by remember { mutableFloatStateOf(0.72f) }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Feedback & Pickers",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = "Badges, progress indicators, snackbar messaging, and native date/time pickers.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        // ---------------------------------------------------------------------------------
        // 1. BADGES (SMALL & NUMERIC)
        // ---------------------------------------------------------------------------------
        item {
            M3ComponentGuidelineCard(
                title = "Badges (Dot & Numeric)",
                tag = "M3 Core",
                category = "Communication",
                usageSummary = "Badges are used to notify users about status changes, unread notifications, or cart counts. Small badges are unlabelled dots for presence; Large badges display numbers (max 999+).",
                doText = "Use a small dot badge when user attention is needed without knowing the exact count.",
                dontText = "Do not place badges on primary call-to-action buttons; use them on icons or navigation destinations.",
                anatomyNotes = "Dot badge: 6dp diameter. Large numeric badge: 16dp height, 4dp horizontal padding.",
                codeSnippet = """
BadgedBox(
    badge = { Badge { Text("9+") } }
) {
    Icon(Icons.Rounded.Notifications, contentDescription = "Alerts")
}
                """.trimIndent()
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Small Dot Badge
                    BadgedBox(
                        badge = { Badge() }
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Notifications,
                            contentDescription = "Notifications",
                            modifier = Modifier.size(28.dp)
                        )
                    }

                    // Numeric Badge (Single Digit)
                    BadgedBox(
                        badge = { Badge { Text("3") } }
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Email,
                            contentDescription = "Inbox",
                            modifier = Modifier.size(28.dp)
                        )
                    }

                    // Numeric Badge (99+)
                    BadgedBox(
                        badge = {
                            Badge(
                                containerColor = MaterialTheme.colorScheme.error,
                                contentColor = MaterialTheme.colorScheme.onError
                            ) {
                                Text("99+")
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.ShoppingCart,
                            contentDescription = "Cart",
                            modifier = Modifier.size(28.dp)
                        )
                    }
                }
            }
        }

        // ---------------------------------------------------------------------------------
        // 2. PROGRESS INDICATORS (LINEAR & CIRCULAR)
        // ---------------------------------------------------------------------------------
        item {
            M3ComponentGuidelineCard(
                title = "Progress Indicators (Linear & Circular)",
                tag = "M3 Core",
                category = "Communication",
                usageSummary = "Progress indicators express an unspecified wait time or display the length of a process. Determinate indicators show known progress percentage (0.0 to 1.0); Indeterminate indicators loop infinitely.",
                doText = "Use determinate indicators when the duration or download size can be accurately computed.",
                dontText = "Do not freeze or hide progress indicators while background operations are still running.",
                anatomyNotes = "Linear height 4dp (active) / 4dp track. Circular diameter 48dp, stroke 4dp.",
                codeSnippet = """
// Linear Progress (Determinate & Indeterminate)
LinearProgressIndicator(progress = { 0.75f }, modifier = Modifier.fillMaxWidth())

// Circular Progress
CircularProgressIndicator()
                """.trimIndent()
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Determinate Linear
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = "Determinate Download: ${(progressVal * 100).toInt()}%",
                            style = MaterialTheme.typography.labelMedium
                        )
                        Spacer(Modifier.height(6.dp))
                        LinearProgressIndicator(
                            progress = { progressVal },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }

                    // Indeterminate Linear
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Text("Indeterminate Network Sync", style = MaterialTheme.typography.labelMedium)
                        Spacer(Modifier.height(6.dp))
                        LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
                    }

                    // Circular
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        CircularProgressIndicator(progress = { progressVal })
                        CircularProgressIndicator()
                    }
                }
            }
        }

        // ---------------------------------------------------------------------------------
        // 3. SNACKBAR WITH ACTIONS
        // ---------------------------------------------------------------------------------
        item {
            M3ComponentGuidelineCard(
                title = "Snackbars & Action Triggers",
                tag = "M3 Core",
                category = "Communication",
                usageSummary = "Snackbars provide brief notifications at the bottom of the screen about an operation, with optional action triggers (e.g. 'Undo'). They disappear automatically after a timeout.",
                doText = "Include an 'Undo' action when the user performs a destructive or reversible action.",
                dontText = "Do not place more than one action button inside a single snackbar.",
                anatomyNotes = "Container height 48dp, corner radius 8dp, inverseSurface container tone.",
                codeSnippet = """
val result = snackbarHostState.showSnackbar(
    message = "Item archived",
    actionLabel = "Undo",
    duration = SnackbarDuration.Short
)
if (result == SnackbarResult.ActionPerformed) {
    // revert action
}
                """.trimIndent()
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Button(
                        onClick = {
                            scope.launch {
                                val result = snackbarHostState.showSnackbar(
                                    message = "Component saved to favorites!",
                                    actionLabel = "Undo"
                                )
                                snackbarResultText = if (result == SnackbarResult.ActionPerformed) {
                                    "User tapped UNDO!"
                                } else {
                                    "Snackbar dismissed automatically."
                                }
                            }
                        }
                    ) {
                        Text("Trigger Snackbar")
                    }

                    if (snackbarResultText.isNotEmpty()) {
                        Text(
                            text = snackbarResultText,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }

                    // Embedded host for demo preview
                    SnackbarHost(hostState = snackbarHostState)
                }
            }
        }

        // ---------------------------------------------------------------------------------
        // 4. DATE PICKER (MODAL DIALOG)
        // ---------------------------------------------------------------------------------
        item {
            M3ComponentGuidelineCard(
                title = "Date Picker (Modal Dialog)",
                tag = "M3 Core",
                category = "Communication / Pickers",
                usageSummary = "Date pickers let users select a date or range of dates. Modal date pickers display an interactive calendar grid inside an accessible dialog.",
                doText = "Format dates according to the user's localized format (e.g. MMM dd, yyyy).",
                dontText = "Do not force users to scroll through decades when entering a date of birth; use text inputs instead.",
                anatomyNotes = "Container width 360dp, corner radius 28dp, surfaceContainerHigh tone.",
                codeSnippet = """
val datePickerState = rememberDatePickerState()
if (showDatePicker) {
    DatePickerDialog(
        onDismissRequest = { showDatePicker = false },
        confirmButton = {
            TextButton(onClick = { showDatePicker = false }) { Text("OK") }
        }
    ) {
        DatePicker(state = datePickerState)
    }
}
                """.trimIndent()
            ) {
                val selectedMillis = datePickerState.selectedDateMillis
                val formattedDate = if (selectedMillis != null) {
                    SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()).format(Date(selectedMillis))
                } else {
                    "None selected"
                }

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "Selected Date: $formattedDate",
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.primary
                    )

                    FilledTonalButton(onClick = { showDatePickerDialog = true }) {
                        Icon(Icons.Rounded.CalendarMonth, contentDescription = null, modifier = Modifier.size(18.dp))
                        Spacer(Modifier.width(8.dp))
                        Text("Open Date Picker Dialog")
                    }

                    if (showDatePickerDialog) {
                        DatePickerDialog(
                            onDismissRequest = { showDatePickerDialog = false },
                            confirmButton = {
                                Button(onClick = { showDatePickerDialog = false }) {
                                    Text("Confirm Date")
                                }
                            },
                            dismissButton = {
                                TextButton(onClick = { showDatePickerDialog = false }) {
                                    Text("Cancel")
                                }
                            }
                        ) {
                            DatePicker(state = datePickerState)
                        }
                    }
                }
            }
        }

        // ---------------------------------------------------------------------------------
        // 5. TIME PICKER (DIAL & INPUT)
        // ---------------------------------------------------------------------------------
        item {
            M3ComponentGuidelineCard(
                title = "Time Picker (Dial & Input Modes)",
                tag = "M3 Core",
                category = "Communication / Pickers",
                usageSummary = "Time pickers allow users to enter a specific time. Dial mode offers a tactile clock face; Input mode allows rapid numeric entry via keypad.",
                doText = "Provide a toggle between clock dial and numeric keyboard input for accessibility.",
                dontText = "Do not lock users into 12h or 24h formats without respecting system locale.",
                anatomyNotes = "Clock face 256dp diameter, hour/minute selector 96x80dp (radius 8dp).",
                codeSnippet = """
val timePickerState = rememberTimePickerState()
TimePicker(state = timePickerState)
                """.trimIndent()
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    val amPm = if (timePickerState.hour < 12) "AM" else "PM"
                    val displayHour = if (timePickerState.hour % 12 == 0) 12 else timePickerState.hour % 12
                    val displayMinute = String.format("%02d", timePickerState.minute)

                    Text(
                        text = "Current Time: $displayHour:$displayMinute $amPm",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.primary
                    )

                    OutlinedButton(onClick = { isTimeInputMode = !isTimeInputMode }) {
                        Icon(Icons.Rounded.AccessTime, contentDescription = null, modifier = Modifier.size(18.dp))
                        Spacer(Modifier.width(8.dp))
                        Text(if (isTimeInputMode) "Switch to Clock Dial" else "Switch to Numeric Input")
                    }

                    if (isTimeInputMode) {
                        TimeInput(state = timePickerState)
                    } else {
                        TimePicker(state = timePickerState)
                    }
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}
