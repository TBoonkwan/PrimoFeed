package com.primo.data.repository

import com.primo.model.FeedItem
import com.primo.model.FeedResponse
import kotlinx.coroutines.flow.Flow

interface FeedRepository {
    suspend fun getFeedList(): Flow<Result<FeedResponse>>
    suspend fun getFeedById(feedId: Int): Flow<Result<FeedItem>>

    fun refreshFeed(): Flow<Result<FeedResponse>>
}
