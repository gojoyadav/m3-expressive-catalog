package com.example.m3catalog

import com.example.m3catalog.ui.model.CatalogDestination
import com.example.m3catalog.ui.viewmodel.CatalogViewModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

/**
 * Unit tests verifying CatalogViewModel state transitions and UDF architecture across all M3 categories.
 */
class CatalogViewModelTest {

    private lateinit var viewModel: CatalogViewModel

    @Before
    fun setUp() {
        viewModel = CatalogViewModel()
    }

    @Test
    fun initialState_isCorrect() {
        val state = viewModel.uiState.value
        assertEquals(CatalogDestination.HOME, state.currentDestination)
        assertFalse(state.isDarkMode)
        assertEquals(0, state.counter)
        assertEquals(42f, state.sliderValue, 0.01f)
    }

    @Test
    fun navigateTo_updatesCurrentDestination() {
        viewModel.navigateTo(CatalogDestination.COMPONENTS)
        assertEquals(CatalogDestination.COMPONENTS, viewModel.uiState.value.currentDestination)

        viewModel.navigateTo(CatalogDestination.TYPOGRAPHY_COLOR)
        assertEquals(CatalogDestination.TYPOGRAPHY_COLOR, viewModel.uiState.value.currentDestination)

        viewModel.navigateTo(CatalogDestination.ACTIONS)
        assertEquals(CatalogDestination.ACTIONS, viewModel.uiState.value.currentDestination)

        viewModel.navigateTo(CatalogDestination.SELECTION)
        assertEquals(CatalogDestination.SELECTION, viewModel.uiState.value.currentDestination)

        viewModel.navigateTo(CatalogDestination.TEXT_INPUT)
        assertEquals(CatalogDestination.TEXT_INPUT, viewModel.uiState.value.currentDestination)

        viewModel.navigateTo(CatalogDestination.CONTAINMENT)
        assertEquals(CatalogDestination.CONTAINMENT, viewModel.uiState.value.currentDestination)

        viewModel.navigateTo(CatalogDestination.NAVIGATION)
        assertEquals(CatalogDestination.NAVIGATION, viewModel.uiState.value.currentDestination)

        viewModel.navigateTo(CatalogDestination.COMMUNICATION)
        assertEquals(CatalogDestination.COMMUNICATION, viewModel.uiState.value.currentDestination)
        viewModel.navigateTo(CatalogDestination.MOTION_SHAPES)
        assertEquals(CatalogDestination.MOTION_SHAPES, viewModel.uiState.value.currentDestination)
        viewModel.navigateTo(CatalogDestination.KOTLIN_LAB)
        assertEquals(CatalogDestination.KOTLIN_LAB, viewModel.uiState.value.currentDestination)
    }

    @Test
    fun toggleDarkMode_flipsBooleanFlag() {
        assertFalse(viewModel.uiState.value.isDarkMode)
        viewModel.toggleDarkMode()
        assertTrue(viewModel.uiState.value.isDarkMode)
        viewModel.toggleDarkMode()
        assertFalse(viewModel.uiState.value.isDarkMode)
    }

    @Test
    fun incrementCounter_increasesValue() {
        assertEquals(0, viewModel.uiState.value.counter)
        viewModel.incrementCounter()
        assertEquals(1, viewModel.uiState.value.counter)
        viewModel.incrementCounter()
        assertEquals(2, viewModel.uiState.value.counter)
    }

    @Test
    fun updateSlider_updatesValue() {
        viewModel.updateSlider(88.5f)
        assertEquals(88.5f, viewModel.uiState.value.sliderValue, 0.01f)
    }
}
