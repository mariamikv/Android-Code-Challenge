package com.mariam.android.challenge.feature.home.vm

import com.mariam.android.challenge.core.domain.models.ItemStateModel
import com.mariam.android.challenge.core.ui.theme.models.UiState
import com.mariam.android.challenge.feature.home.repository.FakeAppRepository
import com.mariam.android.challenge.feature.home.state.HomeScreenState
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HomeScreenViewModelSimpleTest {

    private lateinit var viewModel: HomeScreenViewModel
    private lateinit var fakeRepository: FakeAppRepository

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        fakeRepository = FakeAppRepository()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getItems should update state with cached data and then network success`() = runTest {
        val cachedData = ItemStateModel.Page(
            title = "Cached Page",
            items = listOf(
                ItemStateModel.Text(title = "Cached Text"),
            )
        )

        val networkData = ItemStateModel.Page(
            title = "Network Page",
            items = listOf(
                ItemStateModel.Section(
                    title = "Network Section",
                    items = listOf(
                        ItemStateModel.Text(title = "Text from Network"),
                        ItemStateModel.Image(
                            title = "Network Image",
                            src = "https://example.com/net.jpg"
                        )
                    )
                )
            )
        )

        fakeRepository.cachedItems = cachedData
        fakeRepository.networkResult = Result.success(networkData)

        viewModel = HomeScreenViewModel(fakeRepository)

        advanceUntilIdle()

        val finalState = waitUntil(
            timeoutMs = 1000,
            condition = {
                viewModel.state.value.uiState == UiState.OK &&
                        viewModel.state.value.data == networkData
            }
        )

        finalState.uiState shouldBe UiState.OK
        finalState.data shouldBe networkData
    }

    @Test
    fun `getItems should handle network failure`() = runTest {
        val cachedData = ItemStateModel.Page(
            title = "Cached Only Page",
            items = listOf(
                ItemStateModel.Text(title = "Only Cached Content")
            )
        )

        val exception = RuntimeException("Network failed")

        fakeRepository.cachedItems = cachedData
        fakeRepository.networkResult = Result.failure(exception)

        viewModel = HomeScreenViewModel(fakeRepository)
        advanceUntilIdle()

        val finalState = waitUntil(timeoutMs = 1000) {
            viewModel.state.value.uiState is UiState.Error
        }

        (finalState.uiState is UiState.Error) shouldBe true
        finalState.data shouldBe cachedData
        (finalState.uiState as UiState.Error).throwable shouldBe exception
    }

    // Wait for state to be updated to final state
    suspend fun waitUntil(
        timeoutMs: Long = 1000,
        intervalMs: Long = 50,
        condition: () -> Boolean
    ): HomeScreenState {
        val startTime = System.currentTimeMillis()
        while (System.currentTimeMillis() - startTime < timeoutMs) {
            if (condition()) break
            delay(intervalMs)
        }
        return viewModel.state.value
    }
}
