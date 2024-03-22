package com.primo.data.repository

import com.primo.data.extension.mapToFeed
import com.primo.data.source.local.LocalFeedDataSource
import com.primo.data.source.remote.RemoteFeedDataSource
import com.primo.model.FeedResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class MediumFeedRepository(
    private val localFeedDataSource: LocalFeedDataSource,
    private val remoteFeedDataSource: RemoteFeedDataSource,
) : FeedRepository {
    override suspend fun getFeedList(): Flow<Result<FeedResponse>> = flowOf(localFeedDataSource.getFeedList())

    override fun refreshFeed(): Flow<Result<FeedResponse>> {
        return remoteFeedDataSource.getFeedList().map {
            if (it.isSuccess) {
                val rssFeed = it.getOrNull()
                val feedResponse = rssFeed.mapToFeed()
                localFeedDataSource.saveFeedList(feedResponse)
            } else {
                Result.failure(NullPointerException())
            }
        }
    }
}