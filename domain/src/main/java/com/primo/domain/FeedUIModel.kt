package com.primo.domain

data class FeedUIModel(var title: String, var logo: String, var feedList: List<FeedDetail>)


data class FeedDetail(
    var image: String,
    val title: String,
    var dateTime: String,
    var content: String,
    var category: List<String>,
    var link: String,
    var creator: String
) {

}