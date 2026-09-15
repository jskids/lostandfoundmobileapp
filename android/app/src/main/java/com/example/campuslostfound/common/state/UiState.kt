package com.example.campuslostfound.common.state

/**
 * Reusable sealed interface representing standard UI states across screens and ViewModels.
 *
 * @param T The type of data exposed when the UI state is [Success].
 */
sealed interface UiState<out T> {
    /**
     * Represents a state where data is currently loading or an operation is in progress.
     */
    data object Loading : UiState<Nothing>

    /**
     * Represents a state where data was successfully retrieved or an operation succeeded.
     *
     * @property data The payload data produced by the ViewModel.
     */
    data class Success<out T>(val data: T) : UiState<T>

    /**
     * Represents a state where an error occurred.
     *
     * @property message A user-presentable error message describing what went wrong.
     */
    data class Error(val message: String) : UiState<Nothing>
}
