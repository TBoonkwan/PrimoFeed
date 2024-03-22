package com.primo.domain.mapper

import com.primo.core.Mapper
import com.primo.domain.entity.FeedDetail
import com.primo.domain.entity.FeedUIModel
import com.primo.model.FeedResponse
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

class FeedMapper : Mapper<FeedResponse, FeedUIModel> {
    override fun map(response: FeedResponse): FeedUIModel {
        val simpleDateFormat = SimpleDateFormat("MMM dd, yyyy" , Locale.ENGLISH)
        val feedDetail: MutableList<FeedDetail> = mutableListOf()
        response.items.forEach {
            val date = Date.parse(it.pubDate.orEmpty())
            feedDetail.add(
                FeedDetail(
                    id = it.id,
                    title = it.title.orEmpty(),
                    pubDate = simpleDateFormat.format(date),
                    author = it.author.orEmpty(),
                    categories = it.categories,
                    content = it.content.orEmpty(),
                    link = it.link.orEmpty(),
                    image = it.image.orEmpty(),
                )
            )
        }
        return FeedUIModel(
            title = response.title.orEmpty(),
            logo = response.image.orEmpty(),
            feedList = feedDetail
        )
    }
}
