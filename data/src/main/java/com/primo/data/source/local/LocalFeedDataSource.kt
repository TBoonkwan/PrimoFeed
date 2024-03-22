package com.primo.data.source.local

import com.primo.model.FeedResponse
import com.prof18.rssparser.model.RssChannel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow

class LocalFeedDataSource {
    suspend fun saveFeedList(feedResponse: RssChannel?): Flow<Result<FeedResponse>> {
        TODO("Not yet implemented")
    }
}