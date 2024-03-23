package com.primo.domain.usecase

import com.primo.domain.entity.FeedUIModel
import kotlinx.coroutines.flow.Flow

interface GetLocalFeedListUseCase {
    suspend fun execute(): Flow<Result<FeedUIModel>>
}

interface GetNewFeedListUseCase {
    suspend fun execute(): Flow<Result<FeedUIModel>>
}