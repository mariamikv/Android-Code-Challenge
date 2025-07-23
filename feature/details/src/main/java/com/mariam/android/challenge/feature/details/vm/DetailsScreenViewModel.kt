package com.mariam.android.challenge.feature.details.vm

import androidx.lifecycle.ViewModel
import com.mariam.android.challenge.core.navigation.routes.Details
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel(assistedFactory = DetailsScreenViewModel.Factory::class)
class DetailsScreenViewModel @AssistedInject constructor(
    @Assisted val navKey: Details
) : ViewModel() {

    val id = navKey.data

    @AssistedFactory
    interface Factory {
        fun create(navKey: Details): DetailsScreenViewModel
    }
}