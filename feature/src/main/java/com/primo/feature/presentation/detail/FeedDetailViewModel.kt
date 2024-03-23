package com.primo.feature.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.primo.domain.entity.FeedDetail
import com.primo.domain.usecase.GetFeedByIdUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

sealed class FeedDetailUIState {
    data object Idle : FeedDetailUIState()
    data object Loading : FeedDetailUIState()
    data class Success(val feedDetail: FeedDetail) : FeedDetailUIState()
    data object Error : FeedDetailUIState()
}

class FeedDetailViewModel(private val getFeedByIdUseCase: GetFeedByIdUseCase) : ViewModel() {

    private val _uiState = MutableStateFlow<FeedDetailUIState>(FeedDetailUIState.Idle)
    val uiState: StateFlow<FeedDetailUIState> = _uiState.asStateFlow()
    fun getFeedDetail(feedId: Int?) {
        _uiState.update {
            FeedDetailUIState.Loading
        }

        viewModelScope.launch {
            getFeedByIdUseCase.execute(feedId ?: -1).map { result ->
                if (result.isSuccess) {
                    _uiState.update {
                        FeedDetailUIState.Success(result.getOrThrow())
                    }
                } else {
                    _uiState.update {
                        FeedDetailUIState.Error
                    }
                }
            }.catch {
                _uiState.update {
                    FeedDetailUIState.Error
                }
            }.collect()
        }
    }
}