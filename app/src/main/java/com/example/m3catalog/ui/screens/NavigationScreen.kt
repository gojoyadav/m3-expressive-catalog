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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.Explore
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.m3catalog.ui.components.M3ComponentGuidelineCard

/**
 * =========================================================================================
 * NAVIGATION & BARS GALLERY
 * Source of Truth: https://m3.material.io/components/top-app-bar/overview
 *                  https://m3.material.io/components/bottom-app-bar/overview
 *                  https://m3.material.io/components/navigation-bar/overview
 *                  https://m3.material.io/components/navigation-drawer/overview
 *                  https://m3.material.io/components/tabs/overview
 * =========================================================================================
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationScreen(
    modifier: Modifier = Modifier
) {
    var selectedPrimaryTab by remember { mutableIntStateOf(0) }
    var selectedSecondaryTab by remember { mutableIntStateOf(0) }
    var selectedDrawerIndex by remember { mutableIntStateOf(0) }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Navigation & App Bars",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = "Structural app chrome: 4 top app bar variants, bottom app bars, tabs, and navigation drawers.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        // ---------------------------------------------------------------------------------
        // 1. TOP APP BARS (4 VARIANTS)
        // ---------------------------------------------------------------------------------
        item {
            M3ComponentGuidelineCard(
                title = "Top App Bars (4 Variants)",
                tag = "M3 Core",
                category = "Navigation Component",
                usageSummary = "Top app bars provide content and actions related to the current screen. M3 defines 4 variants: Center-Aligned (primary screens), Small (compact sub-screens), Medium (sub-screens with subtitle), and Large (prominent collapsing editorial headers).",
                doText = "Use LargeTopAppBar on primary hub landing screens to establish bold visual personality.",
                dontText = "Do not place more than 3 action icons in a top app bar; place excess items into an overflow menu.",
                anatomyNotes = "Center/Small: 64dp height. Medium: 112dp height. Large: 152dp height.",
                codeSnippet = """
// Center-Aligned Top App Bar
CenterAlignedTopAppBar(
    title = { Text("Screen Title") },
    navigationIcon = {
        IconButton(onClick = {}) { Icon(Icons.Rounded.Menu, contentDescription = "Menu") }
    },
    actions = {
        IconButton(onClick = {}) { Icon(Icons.Rounded.Search, contentDescription = "Search") }
    }
)
                """.trimIndent()
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    // Center-Aligned
                    CenterAlignedTopAppBar(
                        title = { Text("Center Aligned", style = MaterialTheme.typography.titleMedium) },
                        navigationIcon = {
                            IconButton(onClick = {}) { Icon(Icons.Rounded.Menu, contentDescription = null) }
                        },
                        actions = {
                            IconButton(onClick = {}) { Icon(Icons.Rounded.Search, contentDescription = null) }
                        },
                        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                            containerColor = MaterialTheme.colorScheme.surfaceContainer
                        )
                    )

                    // Small Top App Bar
                    TopAppBar(
                        title = { Text("Small Top Bar", style = MaterialTheme.typography.titleMedium) },
                        navigationIcon = {
                            IconButton(onClick = {}) { Icon(Icons.Rounded.ArrowBack, contentDescription = null) }
                        },
                        actions = {
                            IconButton(onClick = {}) { Icon(Icons.Rounded.MoreVert, contentDescription = null) }
                        },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = MaterialTheme.colorScheme.surfaceContainer
                        )
                    )
                }
            }
        }

        // ---------------------------------------------------------------------------------
        // 2. BOTTOM APP BAR
        // ---------------------------------------------------------------------------------
        item {
            M3ComponentGuidelineCard(
                title = "Bottom App Bar (with Integrated FAB)",
                tag = "M3 Core",
                category = "Navigation Component",
                usageSummary = "Bottom app bars provide access to up to 4 actions and an optional floating action button anchored to the bottom edge. Ideal for media players, note editors, and multi-tool utilities.",
                doText = "Place the most critical positive action in the floating action button slot.",
                dontText = "Do not use a Bottom App Bar and a Navigation Bar simultaneously on the same screen.",
                anatomyNotes = "Container height 80dp, surfaceContainer tone, FAB 56dp placed at trailing edge.",
                codeSnippet = """
BottomAppBar(
    actions = {
        IconButton(onClick = {}) { Icon(Icons.Rounded.Check, contentDescription = null) }
        IconButton(onClick = {}) { Icon(Icons.Rounded.Edit, contentDescription = null) }
    },
    floatingActionButton = {
        FloatingActionButton(onClick = {}) { Icon(Icons.Rounded.Add, contentDescription = null) }
    }
)
                """.trimIndent()
            ) {
                BottomAppBar(
                    actions = {
                        IconButton(onClick = {}) { Icon(Icons.Rounded.Check, contentDescription = null) }
                        IconButton(onClick = {}) { Icon(Icons.Rounded.Edit, contentDescription = null) }
                        IconButton(onClick = {}) { Icon(Icons.Rounded.Favorite, contentDescription = null) }
                    },
                    floatingActionButton = {
                        FloatingActionButton(onClick = {}) {
                            Icon(Icons.Rounded.Add, contentDescription = "Add")
                        }
                    },
                    containerColor = MaterialTheme.colorScheme.surfaceContainer
                )
            }
        }

        // ---------------------------------------------------------------------------------
        // 3. TABS (PRIMARY & SECONDARY)
        // ---------------------------------------------------------------------------------
        item {
            M3ComponentGuidelineCard(
                title = "Tabs (Primary & Secondary)",
                tag = "M3 Core",
                category = "Navigation Component",
                usageSummary = "Tabs organize content across different screens, data sets, and functional interactions. Primary tabs sit directly beneath top app bars; Secondary tabs are used for sub-filtering within a view.",
                doText = "Use Primary Tabs for top-level peer views and Secondary Tabs for nested sub-categories.",
                dontText = "Do not use tabs for swipe-to-dismiss workflows or linear wizard flows.",
                anatomyNotes = "Container height 48dp, active indicator 3dp bar or pill with primary/secondary tones.",
                codeSnippet = """
val titles = listOf("Explore", "Favorites", "Saved")
PrimaryTabRow(selectedTabIndex = selectedTab) {
    titles.forEachIndexed { index, title ->
        Tab(
            selected = selectedTab == index,
            onClick = { selectedTab = index },
            text = { Text(title) }
        )
    }
}
                """.trimIndent()
            ) {
                val primaryTitles = listOf("Overview", "Components", "Tokens")
                val secondaryTitles = listOf("All", "Core M3", "Expressive")

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text("Primary Tab Row", style = MaterialTheme.typography.labelMedium)
                    PrimaryTabRow(selectedTabIndex = selectedPrimaryTab) {
                        primaryTitles.forEachIndexed { index, title ->
                            Tab(
                                selected = selectedPrimaryTab == index,
                                onClick = { selectedPrimaryTab = index },
                                text = { Text(title) }
                            )
                        }
                    }

                    Text("Secondary Tab Row (Sub-categories)", style = MaterialTheme.typography.labelMedium)
                    SecondaryTabRow(selectedTabIndex = selectedSecondaryTab) {
                        secondaryTitles.forEachIndexed { index, title ->
                            Tab(
                                selected = selectedSecondaryTab == index,
                                onClick = { selectedSecondaryTab = index },
                                text = { Text(title) }
                            )
                        }
                    }
                }
            }
        }

        // ---------------------------------------------------------------------------------
        // 4. NAVIGATION DRAWER ITEMS
        // ---------------------------------------------------------------------------------
        item {
            M3ComponentGuidelineCard(
                title = "Navigation Drawer Items",
                tag = "M3 Core",
                category = "Navigation Component",
                usageSummary = "Navigation drawers provide access to destinations and app functionality on phones (modal drawer) and tablets/desktops (standard/permanent drawer).",
                doText = "Include notification badges in the badge slot to alert users of unseen items.",
                dontText = "Do not exceed 10 drawer items without clear section dividers or headings.",
                anatomyNotes = "Container height 56dp, corner radius 28dp (full pill), container color surfaceContainerLow.",
                codeSnippet = """
NavigationDrawerItem(
    label = { Text("Inbox") },
    selected = isSelected,
    onClick = { /* navigate */ },
    icon = { Icon(Icons.Rounded.Home, contentDescription = null) },
    badge = { Text("12") }
)
                """.trimIndent()
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    val drawerItems = listOf(
                        Triple("Expressive Hub", Icons.Rounded.Home, null),
                        Triple("Actions & Buttons", Icons.Rounded.Explore, "New"),
                        Triple("Feedback & Badges", Icons.Rounded.Notifications, "3")
                    )

                    drawerItems.forEachIndexed { index, (label, icon, badge) ->
                        val isSelected = index == selectedDrawerIndex
                        NavigationDrawerItem(
                            label = { Text(label) },
                            selected = isSelected,
                            onClick = { selectedDrawerIndex = index },
                            icon = { Icon(icon, contentDescription = null) },
                            badge = badge?.let {
                                {
                                    Badge { Text(it) }
                                }
                            },
                            colors = NavigationDrawerItemDefaults.colors(
                                selectedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                                unselectedContainerColor = MaterialTheme.colorScheme.surfaceContainerLowest
                            )
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}
