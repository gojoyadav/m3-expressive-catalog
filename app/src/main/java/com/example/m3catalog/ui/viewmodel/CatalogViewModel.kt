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
 * StateFlow is Kotlin Coroutines' native observable state holder. It survives
 * configuration changes, maintains the single source of truth, and emits updates to
 * composables via collectAsStateWithLifecycle().
 * =========================================================================================
 */
data class CatalogUiState(
    val currentDestination: CatalogDestination = CatalogDestination.HOME,
    val backStack: List<CatalogDestination> = listOf(CatalogDestination.HOME),
    val isDarkMode: Boolean = false,
    val sliderValue: Float = 42f,
    val counter: Int = 0,
    val selectedButtonFilter: String = "All",
    val simulatedScreenWidthDp: Int = 360
)

class CatalogViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(CatalogUiState())

    val uiState: StateFlow<CatalogUiState> = _uiState.asStateFlow()

    fun navigateTo(destination: CatalogDestination) {
        if (_uiState.value.currentDestination == destination) return

        _uiState.update { currentState ->
            val updatedStack = if (destination == CatalogDestination.HOME) {
                listOf(CatalogDestination.HOME)
            } else {
                currentState.backStack.filter { it != destination } + destination
            }
            currentState.copy(
                currentDestination = destination,
                backStack = updatedStack
            )
        }
    }

    fun navigateBack(): Boolean {
        val currentStack = _uiState.value.backStack
        if (currentStack.size > 1) {
            val newStack = currentStack.dropLast(1)
            val previousDestination = newStack.last()
            _uiState.update { currentState ->
                currentState.copy(
                    currentDestination = previousDestination,
                    backStack = newStack
                )
            }
            return true
        } else if (_uiState.value.currentDestination != CatalogDestination.HOME) {
            _uiState.update { currentState ->
                currentState.copy(
                    currentDestination = CatalogDestination.HOME,
                    backStack = listOf(CatalogDestination.HOME)
                )
            }
            return true
        }
        return false // At Home root: allow default exit
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
