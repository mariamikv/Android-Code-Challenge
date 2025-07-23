package com.mariam.android.challenge.feature.home.state

import com.mariam.android.challenge.core.domain.models.ItemStateModel
import com.mariam.android.challenge.core.ui.theme.models.UiState

data class HomeScreenState(
    val uiState: UiState = UiState.Loading,
    val data: ItemStateModel? = null,
)