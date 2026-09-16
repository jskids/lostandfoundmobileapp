package com.example.campuslostfound.ui.home

import androidx.lifecycle.viewModelScope
import com.example.campuslostfound.common.state.UiState
import com.example.campuslostfound.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * HomeViewModel manages the UI state of the [HomeScreen] dashboard.
 * It extends [BaseViewModel] and utilizes [UiState] to represent the list of found items on campus,
 * thereby verifying the core application architecture, dependency injection (Hilt), and reactive flows.
 */
@HiltViewModel
class HomeViewModel @Inject constructor() : BaseViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<MockFoundItem>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<MockFoundItem>>> = _uiState.asStateFlow()

    init {
        loadItems()
    }

    fun loadItems() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                // Simulate network/database loading to showcase LoadingScreen and LoadingIndicator
                delay(800)
                _uiState.value = UiState.Success(mockFoundItems)
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.localizedMessage ?: "Failed to load campus items.")
            }
        }
    }
}
