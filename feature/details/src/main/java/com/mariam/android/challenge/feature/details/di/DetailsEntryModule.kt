package com.mariam.android.challenge.feature.details.di

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation3.runtime.entry
import com.mariam.android.challenge.core.navigation.EntryProviderInstaller
import com.mariam.android.challenge.core.navigation.routes.Details
import com.mariam.android.challenge.feature.details.ui.DetailsScreenComponent
import com.mariam.android.challenge.feature.details.vm.DetailsScreenViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(ActivityRetainedComponent::class)
object DetailsEntryModule {

    @IntoSet
    @Provides
    fun provideDetailsEntry(): EntryProviderInstaller = {
        entry<Details> { key ->
            val viewModel = hiltViewModel<DetailsScreenViewModel, DetailsScreenViewModel.Factory> {
                it.create(key)
            }

            DetailsScreenComponent(viewModel)
        }
    }
}
