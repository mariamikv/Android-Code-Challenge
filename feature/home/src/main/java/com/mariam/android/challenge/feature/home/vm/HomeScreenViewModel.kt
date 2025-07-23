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

    private fun getItems() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.getItems(pageId = PAGE_ID)
            }.onSuccess { response ->
                println(response)
                _state.update {
                    it.copy(
                        uiState = UiState.OK,
                        data = response,
                    )
                }
            }.onFailure { throwable ->
                println(throwable)
                _state.update {
                    it.copy(
                        uiState = UiState.Error(throwable),
                    )
                }
            }
        }
    }

    companion object {
        private const val PAGE_ID = "f118b9f0-6f84-435e-85d5-faf4453eb72a"
    }
}