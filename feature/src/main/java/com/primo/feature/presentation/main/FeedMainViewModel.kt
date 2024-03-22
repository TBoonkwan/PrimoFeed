package com.primo.feature.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.primo.domain.entity.FeedUIModel
import com.primo.domain.usecase.GetFeedListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

sealed class FeedUIState {
    data object Idle : FeedUIState()
    data object Loading : FeedUIState()
    data class Success(val feedUIModel: FeedUIModel) : FeedUIState()
    data object Error : FeedUIState()
}

class FeedMainViewModel(private val getFeedListUseCase: GetFeedListUseCase) : ViewModel() {

    private val _uiState = MutableStateFlow<FeedUIState>(FeedUIState.Idle)
    val uiState: StateFlow<FeedUIState> = _uiState.asStateFlow()

    @OptIn(ExperimentalCoroutinesApi::class)
    fun getFeedList() {
        _uiState.update {
            FeedUIState.Loading
        }
        viewModelScope.launch(Dispatchers.IO) {
            getFeedListUseCase.execute().catch {
                _uiState.update {
                    FeedUIState.Error
                }
            }.collect { result ->
                if (result.isSuccess) {
                    _uiState.update {
                        FeedUIState.Success(feedUIModel = result.getOrThrow())
                    }
                } else {
                    _uiState.update {
                        FeedUIState.Error
                    }
                }
            }
        }
    }
}