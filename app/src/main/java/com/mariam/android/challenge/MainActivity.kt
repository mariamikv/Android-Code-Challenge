package com.mariam.android.challenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.navigation3.ui.rememberSceneSetupNavEntryDecorator
import com.mariam.android.challenge.core.navigation.EntryProviderInstaller
import com.mariam.android.challenge.core.navigation.Navigator
import com.mariam.android.challenge.core.ui.theme.ProvideAndroidCodeChallengeTheme
import dagger.hilt.android.AndroidEntryPoint
import jakarta.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var navigator: Navigator
    @Inject
    lateinit var entryProviderBuilders: Set<@JvmSuppressWildcards EntryProviderInstaller>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProvideAndroidCodeChallengeTheme {
                NavDisplay(
                    backStack = navigator.backStack,
                    onBack = { navigator.goBack() },
                    entryDecorators = listOf(
                        rememberSceneSetupNavEntryDecorator(),
                        rememberSavedStateNavEntryDecorator(),
                        rememberViewModelStoreNavEntryDecorator()
                    ),
                    entryProvider = entryProvider {
                        entryProviderBuilders.forEach { it(this) }
                    }
                )
            }
        }
    }
}
