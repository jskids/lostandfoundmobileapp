package com.example.campuslostfound.viewmodel

import androidx.lifecycle.ViewModel

/**
 * BaseViewModel serves as the base class for all ViewModels in the Campus Lost & Found application.
 * It extends the AndroidX [ViewModel] class, ensuring lifecycle awareness and proper coroutine scope management.
 *
 * Guidelines for future feature ViewModels extending [BaseViewModel]:
 * 1. Maintain Uni-directional Data Flow (UDF) by exposing read-only UI state using Kotlin StateFlow.
 * 2. Keep the UI state representation decoupled from Android-specific UI components (e.g., Context, Views).
 * 3. Interact exclusively with domain-layer interfaces (use cases or repositories) rather than directly
 *    calling local database or remote API implementation details.
 * 4. Leverage `viewModelScope` for launching coroutines to ensure automatic cancellation when the ViewModel is cleared.
 */
abstract class BaseViewModel : ViewModel() {
    // Kept minimal to prevent feature-specific business logic leak,
    // in accordance with the application design guidelines.
}
