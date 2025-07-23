package com.mariam.android.challenge.core.ui.theme.models

sealed class UiState {
    object OK : UiState()
    object Loading : UiState()
    data class Error(val throwable: Throwable) : UiState()
}