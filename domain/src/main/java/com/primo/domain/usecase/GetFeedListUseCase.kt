package com.primo.domain.usecase

import com.primo.domain.entity.FeedUIModel
import kotlinx.coroutines.flow.Flow

interface GetFeedListUseCase {
    suspend fun execute(): Flow<Result<FeedUIModel>>
}