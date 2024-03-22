package com.primo.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class FeedUIModel(
    var title: String = "",
    var logo: String = "",
    var feedList: List<FeedDetail> = listOf()
) : Parcelable

@Parcelize
data class FeedDetail(
    var id : Int,
    var image: String,
    val title: String,
    var pubDate: String,
    var content: String,
    var categories: List<String>,
    var link: String,
    var author: String
) : Parcelable