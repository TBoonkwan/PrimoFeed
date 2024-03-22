package com.primo.data.repository

import com.primo.model.FeedResponse
import kotlinx.coroutines.flow.Flow

interface FeedRepository {
    fun getFeedList(): Flow<Result<FeedResponse>>
}