package com.primo.domain.mapper

import com.primo.core.Mapper
import com.primo.domain.entity.FeedDetail
import com.primo.domain.entity.FeedProfile
import com.primo.domain.entity.FeedUIModel
import com.primo.model.FeedResponse
import com.primo.model.formatMediumFeedProfile
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class FeedMapper : Mapper<FeedResponse, FeedUIModel> {
    override fun map(response: FeedResponse): FeedUIModel {
        val newProfile = response.formatMediumFeedProfile()
        val simpleDateFormat = SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH)
        val feedDetail: MutableList<FeedDetail> = mutableListOf()
        response.items.forEach {
            val date = Date.parse(it.pubDate.orEmpty())
            feedDetail.add(
                element = FeedDetail(
                    id = it.id ?: -1,
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
            profile = FeedProfile(
                title = newProfile,
                logo = response.image.orEmpty(),
            ),
            feedList = feedDetail
        )
    }
}
