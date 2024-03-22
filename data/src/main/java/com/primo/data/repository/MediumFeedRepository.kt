package com.primo.data.repository

import com.primo.data.extension.mapToFeed
import com.primo.data.source.local.LocalFeedDataSource
import com.primo.data.source.remote.RemoteFeedDataSource
import com.primo.model.FeedResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class MediumFeedRepository(
    private val localFeedDataSource: LocalFeedDataSource,
    private val remoteFeedDataSource: RemoteFeedDataSource,
) : FeedRepository {
    override suspend fun getFeedList(): Flow<Result<FeedResponse>> {
        val data = localFeedDataSource.getFeedList()
        return if (data.title?.isEmpty() == true) {
            sync()
        } else {
            flowOf(Result.success(data))
        }
    }

    override fun sync(): Flow<Result<FeedResponse>> {
        return remoteFeedDataSource.getFeedList().map {
            if (it.isSuccess) {
                val rssFeed = it.getOrNull()
                val feedResponse = rssFeed.mapToFeed()
                localFeedDataSource.saveFeedList(feedResponse)
                Result.success(feedResponse)
            } else {
                Result.failure(NullPointerException())
            }
        }
    }
}