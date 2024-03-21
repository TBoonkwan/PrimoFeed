package com.primo.domain

data class FeedEntity(var title: String, var feedItem: MutableList<FeedDetail>)


data class FeedDetail(
    var image: String,
    val title: String,
    var dateTime: String,
    var content: String,
    var category: String,
    var link: String,
    var creator: String
) {

}