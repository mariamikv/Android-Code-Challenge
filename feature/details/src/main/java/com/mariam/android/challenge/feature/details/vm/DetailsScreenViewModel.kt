package com.mariam.android.challenge.feature.details.vm

import androidx.lifecycle.ViewModel
import com.mariam.android.challenge.core.navigation.routes.Details
import com.mariam.android.challenge.feature.details.state.DetailsScreenState
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@HiltViewModel(assistedFactory = DetailsScreenViewModel.Factory::class)
class DetailsScreenViewModel @AssistedInject constructor(
    @Assisted val navKey: Details
) : ViewModel() {

    val args = navKey.data

    private val _state = MutableStateFlow(
        DetailsScreenState(
            title = args.title,
            src = args.src,
        ),
    )

    val state: StateFlow<DetailsScreenState>
        get() = _state

    @AssistedFactory
    interface Factory {
        fun create(navKey: Details): DetailsScreenViewModel
    }
}