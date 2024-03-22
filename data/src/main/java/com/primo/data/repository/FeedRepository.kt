package com.primo.data.repository

import com.primo.model.FeedResponse
import kotlinx.coroutines.flow.Flow

interface FeedRepository {
    suspend fun getFeedList(): Flow<Result<FeedResponse>>
    fun refreshFeed(): Flow<Result<FeedResponse>>
}
