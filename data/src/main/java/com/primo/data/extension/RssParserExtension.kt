package com.primo.data.extension

import com.primo.model.FeedItem
import com.primo.model.FeedResponse
import com.prof18.rssparser.model.RssChannel

fun RssChannel?.mapToFeed(): FeedResponse {
    val rssFeed = this
    val feedItems = mutableListOf<FeedItem>()

    rssFeed?.items?.forEach {
        feedItems.add(
            FeedItem(
                title = it.title,
                pubDate = it.pubDate,
                author = it.author,
                categories = it.categories,
                content = it.content,
                link = it.link,
                image = it.image,
            )
        )
    }

    return FeedResponse(
        title = rssFeed?.title,
        image = rssFeed?.image?.url,
        items = feedItems
    )
}