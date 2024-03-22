package com.primo.domain

import com.primo.domain.mapper.FeedMapper
import com.primo.domain.usecase.GetFeedListUseCase
import com.primo.domain.usecase.GetMediumFeedListUseCase
import org.koin.dsl.module

val domainModule = module {
    factory {
        FeedMapper()
    }
    factory<GetFeedListUseCase> {
        GetMediumFeedListUseCase(
            feedMapper = get(),
            feedRepository = get()
        )
    }
}