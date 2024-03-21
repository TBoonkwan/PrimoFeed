package com.primo.domain.mapper

import com.primo.core.Mapper
import com.primo.domain.FeedUIModel
import com.primo.model.FeedResponse

class FeedMapper : Mapper<Result<FeedResponse>, FeedUIModel> {
    override fun map(response: Result<FeedResponse>): FeedUIModel {
        TODO("Not yet implemented")
    }
}
