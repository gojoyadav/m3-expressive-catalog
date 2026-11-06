package com.example.m3catalog.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.History
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Visibility
import androidx.compose.material.icons.rounded.VisibilityOff
import androidx.compose.material3.DockedSearchBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.m3catalog.ui.components.M3ComponentGuidelineCard

/**
 * =========================================================================================
 * TEXT INPUT & SEARCH GALLERY
 * Source of Truth: https://m3.material.io/components/text-fields/overview
 *                  https://m3.material.io/components/search/overview
 * =========================================================================================
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextInputScreen(
    modifier: Modifier = Modifier
) {
    // Text field states
    var filledText by remember { mutableStateOf("alex.rivera@example.com") }
    var passwordText by remember { mutableStateOf("SecretPassword123") }
    var isPasswordVisible by remember { mutableStateOf(false) }
    var validatedText by remember { mutableStateOf("Invalid Input") }
    var multilineText by remember { mutableStateOf("Material 3 Expressive creates deep visual resonance with tactile touch boundaries.") }

    // Search bar state
    var searchQuery by remember { mutableStateOf("") }
    var isSearchActive by remember { mutableStateOf(false) }
    val searchHistory = listOf("Button Sizes", "Surface Containers", "Spring Physics", "Modal Bottom Sheet")

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Text Fields & Search",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = "Data entry fields, password masking, error validation, and interactive search bars.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        // ---------------------------------------------------------------------------------
        // 1. FILLED TEXT FIELD
        // ---------------------------------------------------------------------------------
        item {
            M3ComponentGuidelineCard(
                title = "Filled Text Field",
                tag = "M3 Core",
                category = "Text Input",
                usageSummary = "Filled text fields have higher visual contrast than outlined text fields. They are optimal when placed alongside other components in complex forms, where their solid container makes them stand out.",
                doText = "Provide helpful supporting text or character counts underneath the container.",
                dontText = "Do not rely on placeholder text alone without a persistent floating label.",
                anatomyNotes = "Default height 56dp, corner radius 4dp (top corners only), 1dp underline border.",
                codeSnippet = """
TextField(
    value = text,
    onValueChange = { text = it },
    label = { Text("Email Address") },
    leadingIcon = { Icon(Icons.Rounded.Email, contentDescription = null) },
    trailingIcon = {
        if (text.isNotEmpty()) {
            IconButton(onClick = { text = "" }) {
                Icon(Icons.Rounded.Clear, contentDescription = "Clear")
            }
        }
    },
    supportingText = { Text("We will never share your email") },
    singleLine = true,
    modifier = Modifier.fillMaxWidth()
)
                """.trimIndent()
            ) {
                TextField(
                    value = filledText,
                    onValueChange = { filledText = it },
                    label = { Text("Email Address") },
                    leadingIcon = {
                        Icon(Icons.Rounded.Email, contentDescription = null)
                    },
                    trailingIcon = {
                        if (filledText.isNotEmpty()) {
                            IconButton(onClick = { filledText = "" }) {
                                Icon(Icons.Rounded.Clear, contentDescription = "Clear text")
                            }
                        }
                    },
                    supportingText = { Text("We will never share your email") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        // ---------------------------------------------------------------------------------
        // 2. OUTLINED TEXT FIELD & PASSWORD MASKING
        // ---------------------------------------------------------------------------------
        item {
            M3ComponentGuidelineCard(
                title = "Outlined Text Field & Password Masking",
                tag = "M3 Core",
                category = "Text Input",
                usageSummary = "Outlined text fields have a clean, minimalist 1dp stroke and transparent/surface background. They are ideal for high-density forms or minimalist designs where filled fields would feel heavy.",
                doText = "Provide an interactive visibility toggle icon on sensitive password fields.",
                dontText = "Do not leave validation errors without an explicit error message explaining how to fix it.",
                anatomyNotes = "Border stroke 1dp (unfocused), 2dp (focused). Corner radius 8dp or 16dp in M3 Expressive.",
                codeSnippet = """
OutlinedTextField(
    value = password,
    onValueChange = { password = it },
    label = { Text("Password") },
    visualTransformation = if (visible) VisualTransformation.None else PasswordVisualTransformation(),
    trailingIcon = {
        IconButton(onClick = { visible = !visible }) {
            Icon(if (visible) Icons.Rounded.Visibility else Icons.Rounded.VisibilityOff, contentDescription = null)
        }
    },
    singleLine = true,
    modifier = Modifier.fillMaxWidth()
)
                """.trimIndent()
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    // Password
                    OutlinedTextField(
                        value = passwordText,
                        onValueChange = { passwordText = it },
                        label = { Text("Secure Password") },
                        leadingIcon = { Icon(Icons.Rounded.Lock, contentDescription = null) },
                        visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        trailingIcon = {
                            IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                                Icon(
                                    imageVector = if (isPasswordVisible) Icons.Rounded.Visibility else Icons.Rounded.VisibilityOff,
                                    contentDescription = "Toggle password visibility"
                                )
                            }
                        },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )

                    // Error Validation Field
                    val isError = validatedText.length < 5
                    OutlinedTextField(
                        value = validatedText,
                        onValueChange = { validatedText = it },
                        label = { Text("Required Username") },
                        isError = isError,
                        supportingText = {
                            if (isError) {
                                Text("Username must be at least 5 characters", color = MaterialTheme.colorScheme.error)
                            } else {
                                Text("${validatedText.length}/20 characters")
                            }
                        },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }

        // ---------------------------------------------------------------------------------
        // 3. MULTILINE TEXT AREA
        // ---------------------------------------------------------------------------------
        item {
            M3ComponentGuidelineCard(
                title = "Multiline Text Area",
                tag = "M3 Core",
                category = "Text Input",
                usageSummary = "Multiline text fields allow users to enter longer strings of text (notes, reviews, descriptions) with automatic text wrapping and vertical expansion.",
                doText = "Set `minLines` to at least 3 so users immediately recognize the field accepts multiline content.",
                dontText = "Do not hide scroll bars or prevent scrolling when text exceeds the container height.",
                anatomyNotes = "Default height matches minLines * line height + 16dp top/bottom padding.",
                codeSnippet = """
OutlinedTextField(
    value = multilineText,
    onValueChange = { multilineText = it },
    label = { Text("Design Notes") },
    minLines = 3,
    maxLines = 6,
    modifier = Modifier.fillMaxWidth()
)
                """.trimIndent()
            ) {
                OutlinedTextField(
                    value = multilineText,
                    onValueChange = { multilineText = it },
                    label = { Text("Design System Notes") },
                    minLines = 3,
                    maxLines = 5,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        // ---------------------------------------------------------------------------------
        // 4. M3 SEARCH BAR & SEARCH VIEW
        // ---------------------------------------------------------------------------------
        item {
            M3ComponentGuidelineCard(
                title = "M3 Search Bar & Suggestions",
                tag = "M3 Core",
                category = "Navigation / Search",
                usageSummary = "The Search Bar provides a prominent, persistent search anchor that opens an interactive search view. It supports real-time suggestions, search history, and clear actions.",
                doText = "Use a full-bleed SearchBar at the top of content catalogs for high-speed discovery.",
                dontText = "Do not place a SearchBar inside nested scrollable cards where it can scroll off-screen unintentionally.",
                anatomyNotes = "Bar height 56dp, corner radius 28dp (full pill), container color surfaceContainerHigh.",
                codeSnippet = """
DockedSearchBar(
    query = query,
    onQueryChange = { query = it },
    onSearch = { isSearchActive = false },
    active = isSearchActive,
    onActiveChange = { isSearchActive = it },
    placeholder = { Text("Search components...") },
    leadingIcon = { Icon(Icons.Rounded.Search, contentDescription = null) }
) {
    searchHistory.forEach { item ->
        ListItem(
            headlineContent = { Text(item) },
            leadingContent = { Icon(Icons.Rounded.History, contentDescription = null) }
        )
    }
}
                """.trimIndent()
            ) {
                DockedSearchBar(
                    query = searchQuery,
                    onQueryChange = { searchQuery = it },
                    onSearch = { isSearchActive = false },
                    active = isSearchActive,
                    onActiveChange = { isSearchActive = it },
                    placeholder = { Text("Search M3 components...") },
                    leadingIcon = {
                        Icon(Icons.Rounded.Search, contentDescription = null)
                    },
                    trailingIcon = {
                        if (searchQuery.isNotEmpty()) {
                            IconButton(onClick = { searchQuery = "" }) {
                                Icon(Icons.Rounded.Clear, contentDescription = "Clear search")
                            }
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    searchHistory.forEach { item ->
                        ListItem(
                            headlineContent = { Text(item) },
                            leadingContent = { Icon(Icons.Rounded.History, contentDescription = null) },
                            modifier = Modifier.clickable {
                                searchQuery = item
                                isSearchActive = false
                            }
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}
