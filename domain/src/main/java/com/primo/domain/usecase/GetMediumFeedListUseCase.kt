package com.primo.domain.usecase

import com.primo.data.repository.FeedRepository
import com.primo.domain.entity.FeedUIModel
import com.primo.domain.mapper.FeedMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetMediumFeedListUseCase(
    private val feedRepository: FeedRepository,
    private val feedMapper: FeedMapper
) : GetLocalFeedListUseCase {
    override suspend fun execute(): Flow<Result<FeedUIModel>> {
        return feedRepository.getFeedList().map {
            if (it.isSuccess) {
                Result.success(feedMapper.map(it.getOrThrow()))
            } else {
                Result.failure(NullPointerException())
            }
        }
    }
}

class RefreshFeedListUseCase(
    private val feedRepository: FeedRepository,
    private val feedMapper: FeedMapper
) : GetNewFeedListUseCase {
    override suspend fun execute(): Flow<Result<FeedUIModel>> {
        return feedRepository.refreshFeed().map {
            if (it.isSuccess) {
                Result.success(feedMapper.map(it.getOrThrow()))
            } else {
                Result.failure(NullPointerException())
            }
        }
    }
}