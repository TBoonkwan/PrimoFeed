package com.primo.feature.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.primo.domain.entity.FeedUIModel
import com.primo.domain.usecase.GetLocalFeedListUseCase
import com.primo.domain.usecase.GetNewFeedListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

sealed class FeedUIState {
    data object Idle : FeedUIState()
    data object Loading : FeedUIState()
    data class Success(val feedUIModel: FeedUIModel) : FeedUIState()
    data object Error : FeedUIState()
}

class FeedMainViewModel(
    private val getFeedListUseCase: GetLocalFeedListUseCase,
    private val refreshFeedListUseCase: GetNewFeedListUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow<FeedUIState>(FeedUIState.Idle)
    val uiState: StateFlow<FeedUIState> = _uiState.asStateFlow()

    @OptIn(ExperimentalCoroutinesApi::class)
    fun getFeedList() {
        _uiState.update {
            FeedUIState.Loading
        }

        viewModelScope.launch(Dispatchers.IO) {
            getFeedListUseCase.execute().map { result ->
                updateUI(result = result, remote = false)
            }.catch {
                _uiState.update {
                    FeedUIState.Error
                }
            }.flatMapMerge {
                refreshFeedListUseCase.execute().map { result ->
                    updateUI(result = result)
                }
            }.distinctUntilChanged().collect()
        }
    }

    private fun updateUI(result: Result<FeedUIModel>, remote: Boolean = true) {
        if (result.isSuccess) {
            _uiState.update {
                FeedUIState.Success(feedUIModel = result.getOrThrow())
            }
        } else {
            if (remote) {
                _uiState.update {
                    FeedUIState.Error
                }
            }
        }
    }
}