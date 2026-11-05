package com.example.m3catalog

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.m3catalog.ui.components.M3ExpressiveBottomBar
import com.example.m3catalog.ui.components.M3ExpressiveDrawerSheet
import com.example.m3catalog.ui.components.M3ExpressiveTopBar
import com.example.m3catalog.ui.model.CatalogDestination
import com.example.m3catalog.ui.screens.ActionsScreen
import com.example.m3catalog.ui.screens.AdaptiveLayoutScreen
import com.example.m3catalog.ui.screens.CommunicationScreen
import com.example.m3catalog.ui.screens.ContainmentScreen
import com.example.m3catalog.ui.screens.ExpressiveComponentsScreen
import com.example.m3catalog.ui.screens.HomeScreen
import com.example.m3catalog.ui.screens.KotlinPlaygroundScreen
import com.example.m3catalog.ui.screens.MotionShapesScreen
import com.example.m3catalog.ui.screens.NavigationScreen
import com.example.m3catalog.ui.screens.SelectionScreen
import com.example.m3catalog.ui.screens.TextInputScreen
import com.example.m3catalog.ui.screens.TypographyColorScreen
import com.example.m3catalog.ui.theme.M3ExpressiveTheme
import com.example.m3catalog.ui.viewmodel.CatalogViewModel
import kotlinx.coroutines.launch

/**
 * =========================================================================================
 * MAIN ACTIVITY & APPLICATION ENTRY POINT
 *
 * BEGINNER GUIDE:
 * 1. `ComponentActivity`: The modern base class for Jetpack Compose apps. Notice we don't
 *    use XML layouts or `setContentView(R.layout.activity_main)`. Instead, we call `setContent { }`!
 *
 * 2. `enableEdgeToEdge()`: Mandated for Android 15+. Draws the app directly underneath
 *    the status bar and gesture navigation bar for a seamless, edge-to-edge canvas.
 *
 * 3. `collectAsStateWithLifecycle()`:
 *    Native Android Mandate. Unlike raw `collectAsState()`, this automatically pauses collection
 *    when the app goes to the background (saving battery & CPU) and resumes when the app returns.
 * =========================================================================================
 */
class MainActivity : ComponentActivity() {

    private val viewModel: CatalogViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Draw edge-to-edge behind system navigation and status bars
        enableEdgeToEdge()

        setContent {
            // Collect the UI state in a lifecycle-aware manner
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()

            M3ExpressiveTheme(darkTheme = uiState.isDarkMode) {
                CatalogApp(
                    currentDestination = uiState.currentDestination,
                    isDarkMode = uiState.isDarkMode,
                    onNavigate = { destination -> viewModel.navigateTo(destination) },
                    onToggleDarkMode = { viewModel.toggleDarkMode() }
                )
            }
        }
    }
}

/**
 * The master App Composable orchestrating the Scaffold, TopBar, Navigation, Drawer and Screens.
 *
 * @param currentDestination The currently active screen destination.
 * @param isDarkMode Whether dark mode is toggled on.
 * @param onNavigate Lambda callback invoked when user switches screens.
 * @param onToggleDarkMode Lambda callback to toggle light/dark theme.
 */
@Composable
fun CatalogApp(
    currentDestination: CatalogDestination,
    isDarkMode: Boolean,
    onNavigate: (CatalogDestination) -> Unit,
    onToggleDarkMode: () -> Unit
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            M3ExpressiveDrawerSheet(
                currentDestination = currentDestination,
                onNavigate = onNavigate,
                onCloseDrawer = { scope.launch { drawerState.close() } }
            )
        }
    ) {
        // Scaffold provides the standard Material structural layout slots:
        // topBar, bottomBar, floatingActionButton, snackbarHost, and content window insets!
        Scaffold(
            topBar = {
                M3ExpressiveTopBar(
                    title = currentDestination.title,
                    subtitle = "Material 3 Expressive Catalog",
                    isDarkMode = isDarkMode,
                    onToggleDarkMode = onToggleDarkMode,
                    onOpenDrawer = { scope.launch { drawerState.open() } }
                )
            },
            bottomBar = {
                M3ExpressiveBottomBar(
                    currentDestination = currentDestination,
                    onNavigate = onNavigate,
                    onOpenDrawer = { scope.launch { drawerState.open() } }
                )
            }
        ) { innerPadding ->
            // innerPadding accounts for the top app bar height and bottom navigation bar height.
            // We pass it to Modifier.padding(innerPadding) so content is never obscured!
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                // Crossfade provides an expressive, fluid crossfade animation between destinations
                Crossfade(
                    targetState = currentDestination,
                    label = "ScreenTransition"
                ) { destination ->
                    when (destination) {
                        CatalogDestination.HOME -> HomeScreen(onNavigate = onNavigate)
                        CatalogDestination.ACTIONS -> ActionsScreen()
                        CatalogDestination.SELECTION -> SelectionScreen()
                        CatalogDestination.TEXT_INPUT -> TextInputScreen()
                        CatalogDestination.CONTAINMENT -> ContainmentScreen()
                        CatalogDestination.NAVIGATION -> NavigationScreen()
                        CatalogDestination.COMMUNICATION -> CommunicationScreen()
                        CatalogDestination.TYPOGRAPHY_COLOR -> TypographyColorScreen()
                        CatalogDestination.ADAPTIVE_LAYOUTS -> AdaptiveLayoutScreen()
                        CatalogDestination.COMPONENTS -> ExpressiveComponentsScreen()
                        CatalogDestination.MOTION_SHAPES -> MotionShapesScreen()
                        CatalogDestination.KOTLIN_LAB -> KotlinPlaygroundScreen()
                    }
                }
            }
        }
    }
}
