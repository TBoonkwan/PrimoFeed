package com.primo.data.source.remote

import com.prof18.rssparser.RssParser
import com.prof18.rssparser.model.RssChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RemoteFeedDataSource {
    fun getFeedList(): Flow<Result<RssChannel>> = flow {
        val url = "https://medium.com/feed/@primoapp"
        val rssParser = RssParser()
        val rssChannel: RssChannel = rssParser.getRssChannel(url)
        emit(Result.success(rssChannel))
    }
}