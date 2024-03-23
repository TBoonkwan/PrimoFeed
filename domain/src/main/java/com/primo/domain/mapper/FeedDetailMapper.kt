package com.primo.domain.mapper

import com.primo.core.Mapper
import com.primo.domain.entity.FeedDetail
import com.primo.domain.entity.FeedDetailUIModel
import com.primo.domain.entity.FeedProfile
import com.primo.model.FeedResponse
import com.primo.model.formatContent
import com.primo.model.formatMediumFeedProfile
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class FeedDetailMapper : Mapper<FeedResponse?, FeedDetailUIModel> {
    override fun map(response: FeedResponse?): FeedDetailUIModel {
        val item = response?.items?.first()
        val simpleDateFormat = SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH)
        val date = Date.parse(item?.pubDate.orEmpty())
        val newContent = item?.formatContent().orEmpty()
        val newProfile = response?.formatMediumFeedProfile().orEmpty()
        return FeedDetailUIModel(
            profile = FeedProfile(
                title = newProfile,
                logo = response?.image.orEmpty()
            ),
            feedDetail = FeedDetail(
                id = item?.id ?: -1,
                title = item?.title.orEmpty(),
                pubDate = simpleDateFormat.format(date),
                author = item?.author.orEmpty(),
                categories = item?.categories ?: listOf(),
                content = newContent,
                link = item?.link.orEmpty(),
                image = item?.image.orEmpty(),
            )
        )
    }
}
