package com.primo.domain.entity

data class FeedUIModel(
    var title: String = "",
    var logo: String = "",
    var feedList: List<FeedDetail> = listOf()
)

data class FeedDetail(
    var image: String,
    val title: String,
    var pubDate: String,
    var content: String,
    var categories: List<String>,
    var link: String,
    var author: String
)