package com.primo.data.repository

import com.primo.data.extension.mapToFeed
import com.primo.data.source.local.LocalFeedDataSource
import com.primo.data.source.remote.RemoteFeedDataSource
import com.primo.model.FeedResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNot
import kotlinx.coroutines.flow.map

class MediumFeedRepository(
    private val localFeedDataSource: LocalFeedDataSource,
    private val remoteFeedDataSource: RemoteFeedDataSource,
) : FeedRepository {
    override fun getFeedList(): Flow<Result<FeedResponse>> {
        return remoteFeedDataSource.getFeedList().filterNot { it.isFailure }.map {
            if (it.isSuccess) {
                val rssFeed = it.getOrNull()
                val feedResponse = rssFeed.mapToFeed()
                Result.success(feedResponse)
            } else {
                Result.failure(NullPointerException())
            }
        }
    }
}