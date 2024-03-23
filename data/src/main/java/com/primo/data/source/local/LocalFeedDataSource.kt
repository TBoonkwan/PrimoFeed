package com.primo.data.source.local

import com.primo.database.dao.Feed
import com.primo.database.dao.FeedDao
import com.primo.database.dao.Profile
import com.primo.model.FeedItem
import com.primo.model.FeedResponse

class LocalFeedDataSource(private val feedDao: FeedDao) {
    suspend fun getFeedList(): Result<FeedResponse> {
        val profile = feedDao.getProfile()
        val feed = feedDao.getFeed()

        if (profile.isEmpty() && feed.isEmpty()) {
            return Result.failure(NullPointerException())
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

        return Result.success(
            FeedResponse(
                title = profile.first().title, image = profile.first().image, items = temp
            )
        )
    }

    suspend fun getFeedDetail(feedId: Int): Result<FeedItem> {
        val feed = feedDao.getFeedById(feedId)
        return Result.success(
            FeedItem(
                id = feed.id ?: -1,
                image = feed.image,
                title = feed.title,
                categories = feed.categories,
                pubDate = feed.pubDate,
                author = feed.author,
                content = feed.content,
                link = feed.link,
            )
        )
    }

    suspend fun saveFeedList(feedResponse: FeedResponse): Result<FeedResponse> {
        if (feedDao.getProfile().isEmpty()) {
            feedDao.insertProfile(
                Profile(
                    title = feedResponse.title,
                    image = feedResponse.image,
                )
            )
        }

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

        return getFeedList()
    }
}