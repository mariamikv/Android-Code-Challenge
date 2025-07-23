package com.mariam.android.challenge.feature.home.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mariam.android.challenge.core.domain.repository.AppRepository
import com.mariam.android.challenge.core.ui.theme.models.UiState
import com.mariam.android.challenge.feature.home.state.HomeScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel()
class HomeScreenViewModel @Inject constructor(
    private val repository: AppRepository,
) : ViewModel() {
    private val _state = MutableStateFlow(
        HomeScreenState(),
    )

    val state: StateFlow<HomeScreenState>
        get() = _state

    init {
        getItems()
    }

    fun retryItems() {
        getItems()
    }

    private fun getItems() {
        viewModelScope.launch {
            val cached = withContext(Dispatchers.IO) {
                repository.getItemsFromDb()
            }

            println(cached)

            _state.update {
                it.copy(
                    uiState = UiState.OK,
                    data = cached,
                )
            }

            withContext(Dispatchers.IO) {
                repository.getItems(pageId = PAGE_ID)
            }.onSuccess { response ->
                _state.update {
                    it.copy(
                        uiState = UiState.OK,
                        data = response,
                    )
                }
            }.onFailure { throwable ->
                _state.update {
                    it.copy(
                        uiState = UiState.Error(throwable),
                        data = cached,
                    )
                }
            }
        }
    }

    companion object {
        private const val PAGE_ID = "f118b9f0-6f84-435e-85d5-faf4453eb72a"
    }
}