package com.primo.domain.mapper

import com.primo.core.Mapper
import com.primo.domain.entity.FeedDetail
import com.primo.model.FeedItem
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class FeedDetailMapper : Mapper<FeedItem?, FeedDetail> {
    override fun map(response: FeedItem?): FeedDetail {
        val simpleDateFormat = SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH)
        val date = Date.parse(response?.pubDate.orEmpty())
        val figureRegex = Regex(pattern = "<figure>(.*?)</figure>")
        val imgRegex = Regex(pattern = "<img (.*?)>")
        var newContent = figureRegex.replace(response?.content.orEmpty(), "")
        newContent = imgRegex.replace(newContent,"")
        return FeedDetail(
            id = response?.id ?: -1,
            title = response?.title.orEmpty(),
            pubDate = simpleDateFormat.format(date),
            author = response?.author.orEmpty(),
            categories = response?.categories ?: listOf(),
            content = newContent,
            link = response?.link.orEmpty(),
            image = response?.image.orEmpty(),
        )
    }
}
