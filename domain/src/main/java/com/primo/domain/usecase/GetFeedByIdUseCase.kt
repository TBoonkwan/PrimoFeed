package com.primo.domain.usecase

import com.primo.data.repository.FeedRepository
import com.primo.domain.entity.FeedDetail
import com.primo.domain.mapper.FeedDetailMapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

interface GetFeedByIdUseCase {
    suspend fun execute(feedId: Int): Flow<Result<FeedDetail>>
}

class GetMediumFeedByIdUseCase(
    private val feedRepository: FeedRepository,
    private val feedMapper: FeedDetailMapper,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : GetFeedByIdUseCase {
    override suspend fun execute(feedId: Int): Flow<Result<FeedDetail>> {
        return feedRepository.getFeedById(feedId).map { result ->
            if (result.isSuccess) {
                val feedResponse = result.getOrThrow()
                val feedDetail = feedMapper.map(feedResponse)
                Result.success(feedDetail)
            } else {
                Result.failure(NullPointerException())
            }
        }.flowOn(dispatcher)
    }
}