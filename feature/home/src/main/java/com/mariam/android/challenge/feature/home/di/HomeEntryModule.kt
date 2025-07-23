package com.mariam.android.challenge.feature.home.di

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation3.runtime.entry
import com.mariam.android.challenge.core.navigation.EntryProviderInstaller
import com.mariam.android.challenge.core.navigation.Navigator
import com.mariam.android.challenge.core.navigation.args.DetailsScreenArgs
import com.mariam.android.challenge.core.navigation.routes.Details
import com.mariam.android.challenge.core.navigation.routes.Home
import com.mariam.android.challenge.feature.home.ui.HomeScreenComponent
import com.mariam.android.challenge.feature.home.vm.HomeScreenViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(ActivityRetainedComponent::class)
object HomeEntryModule {

    @IntoSet
    @Provides
    fun provideHomeEntry(navigator: Navigator): EntryProviderInstaller = {
        entry<Home> {
            val viewModel: HomeScreenViewModel = hiltViewModel()

            HomeScreenComponent(
                viewModel = viewModel,
                onNavigationActionClick = { title, src ->
                    navigator.goTo(
                        Details(
                            data = DetailsScreenArgs(
                                title = title,
                                imageUrl = src,
                            )
                        )
                    )
                },
            )
        }
    }
}
