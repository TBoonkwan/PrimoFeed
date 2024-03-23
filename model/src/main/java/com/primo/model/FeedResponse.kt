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

fun FeedItem.formatContent() : String{
    val figureRegex = Regex(pattern = "<figure>(.*?)</figure>")
    val imgRegex = Regex(pattern = "<img (.*?)>")
    var newContent = figureRegex.replace(this.content.orEmpty(), "")
    newContent = imgRegex.replace(newContent, "")
    return newContent
}

fun FeedResponse.formatMediumFeedProfile(): String {
    val storiesRegex = Regex(pattern = "Stories by (.*?)")
    val endProfileRegex = Regex(pattern = " on Medium(.*?)")
    var newProfile = storiesRegex.replace(this.title.orEmpty(), "")
    newProfile = newProfile.replace(endProfileRegex, "")
    return newProfile
}