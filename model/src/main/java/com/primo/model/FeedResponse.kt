package com.primo.model

data class FeedResponse(
    val title: String?,
    val image: String?,
    val items: List<FeedItem> = listOf()
)

data class FeedItem(
    var id: Int? = null,
    var image: String?,
    val title: String?,
    var pubDate: String?,
    var content: String?,
    var categories: List<String> = listOf(),
    var link: String?,
    var author: String?
)