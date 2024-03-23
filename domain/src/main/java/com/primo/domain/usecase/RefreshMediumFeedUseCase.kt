package com.primo.domain.usecase

import com.primo.data.repository.FeedRepository
import com.primo.domain.entity.FeedUIModel
import com.primo.domain.mapper.FeedMapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

interface RefreshFeedUseCase {
    suspend fun execute(): Flow<Result<FeedUIModel>>
}

class RefreshMediumFeedUseCase(
    private val feedRepository: FeedRepository,
    private val feedMapper: FeedMapper,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : RefreshFeedUseCase {
    override suspend fun execute(): Flow<Result<FeedUIModel>> {
        return feedRepository.refreshFeed().map {
            if (it.isSuccess) {
                Result.success(feedMapper.map(it.getOrThrow()))
            } else {
                Result.failure(NullPointerException())
            }
        }.flowOn(dispatcher)
    }
}