package com.primo.domain.mapper

import com.primo.core.Mapper
import com.primo.domain.FeedEntity
import com.primo.model.FeedResponse

class FeedMapper : Mapper<Result<FeedResponse>, FeedEntity> {
    override fun map(response: Result<FeedResponse>): FeedEntity {
        TODO("Not yet implemented")
    }
}
