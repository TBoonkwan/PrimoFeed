package com.primo.domain

import com.primo.domain.mapper.FeedMapper
import com.primo.domain.usecase.GetLocalFeedListUseCase
import com.primo.domain.usecase.GetMediumFeedListUseCase
import com.primo.domain.usecase.GetNewFeedListUseCase
import com.primo.domain.usecase.RefreshFeedListUseCase
import org.koin.dsl.module

val domainModule = module {
    factory {
        FeedMapper()
    }
    factory<GetLocalFeedListUseCase> {
        GetMediumFeedListUseCase(
            feedMapper = get(),
            feedRepository = get()
        )
    }
    factory<GetNewFeedListUseCase> {
        RefreshFeedListUseCase(
            feedMapper = get(),
            feedRepository = get()
        )
    }
}