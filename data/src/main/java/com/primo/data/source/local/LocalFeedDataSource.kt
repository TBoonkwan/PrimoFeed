package com.primo.data.source.local

import com.primo.database.dao.Feed
import com.primo.database.dao.FeedDao
import com.primo.database.dao.Profile
import com.primo.model.FeedItem
import com.primo.model.FeedResponse

class LocalFeedDataSource(private val feedDao: FeedDao) {
    suspend fun getFeedList(): FeedResponse {
        val profile = feedDao.getProfile()
        val feed = feedDao.getFeed()

        if (profile.isEmpty() && feed.isEmpty()) {
            return FeedResponse(title = "", image = "", items = listOf())
        }

        val temp: MutableList<FeedItem> = mutableListOf()
        feed.map {
            temp.add(
                FeedItem(
                    id = it.id ?: -1,
                    image = it.image,
                    title = it.title,
                    categories = it.categories,
                    pubDate = it.pubDate,
                    author = it.author,
                    content = it.content,
                    link = it.link,
                )
            )
        }
        return FeedResponse(
            title = profile.first().title, image = profile.first().image, items = temp
        )
    }

    suspend fun saveFeedList(feedResponse: FeedResponse) {
        feedDao.insertProfile(
            Profile(
                title = feedResponse.title,
                image = feedResponse.image,
            )
        )

        val profile = feedDao.getProfile()

        feedResponse.items.forEach {
            val feed = Feed(
                image = it.image,
                title = it.title,
                link = it.link,
                content = it.content,
                author = it.author,
                categories = it.categories,
                profileId = profile.first().id,
                pubDate = it.pubDate,
            )
            feedDao.insertFeed(feed)
        }
    }
}