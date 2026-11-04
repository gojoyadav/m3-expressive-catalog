package com.example.m3catalog.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.m3catalog.ui.model.CatalogDestination
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * =========================================================================================
 * BEGINNER GUIDE: STATE MANAGEMENT & UNIDIRECTIONAL DATA FLOW (UDF)
 *
 * Why use a ViewModel?
 * When a user rotates their phone, Android destroys and recreates the Activity.
 * A ViewModel survives configuration changes (rotation, theme toggling, language switch).
 * Keeping your state here guarantees you never lose user progress!
 *
 * What is StateFlow?
 * `StateFlow` is Kotlin Coroutines' native observable state holder. It always holds a current value,
 * emits updates to observers, and only triggers recomposition when the value actually changes.
 *
 * Unidirectional Data Flow (UDF):
 * 1. UI (Composable) renders based on `CatalogUiState`.
 * 2. User taps a button or moves a slider -> UI calls a method on `CatalogViewModel`.
 * 3. ViewModel updates `_uiState` atomically.
 * 4. Compose observes the new state via `collectAsStateWithLifecycle()` and recomposes the UI!
 * =========================================================================================
 */

data class CatalogUiState(
    val currentDestination: CatalogDestination = CatalogDestination.HOME,
    val isDarkMode: Boolean = false,
    val sliderValue: Float = 42f,
    val counter: Int = 0,
    val selectedButtonFilter: String = "All",
    val simulatedScreenWidthDp: Int = 360 // Allows simulating Compact (<600dp), Medium (600-840dp), Expanded (>840dp)
)

class CatalogViewModel : ViewModel() {

    // Backing property: Private mutable state that only this ViewModel can modify
    private val _uiState = MutableStateFlow(CatalogUiState())

    // Public read-only StateFlow exposed to Composables
    val uiState: StateFlow<CatalogUiState> = _uiState.asStateFlow()

    fun navigateTo(destination: CatalogDestination) {
        _uiState.update { currentState ->
            currentState.copy(currentDestination = destination)
        }
    }

    fun toggleDarkMode() {
        _uiState.update { currentState ->
            currentState.copy(isDarkMode = !currentState.isDarkMode)
        }
    }

    fun updateSlider(newValue: Float) {
        _uiState.update { currentState ->
            currentState.copy(sliderValue = newValue)
        }
    }

    fun incrementCounter() {
        _uiState.update { currentState ->
            currentState.copy(counter = currentState.counter + 1)
        }
    }

    fun selectButtonFilter(filter: String) {
        _uiState.update { currentState ->
            currentState.copy(selectedButtonFilter = filter)
        }
    }

    fun setSimulatedScreenWidth(widthDp: Int) {
        _uiState.update { currentState ->
            currentState.copy(simulatedScreenWidthDp = widthDp)
        }
    }
}
