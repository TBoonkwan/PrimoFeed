package com.primo.domain

import com.primo.domain.mapper.FeedDetailMapper
import com.primo.domain.mapper.FeedMapper
import com.primo.domain.usecase.GetFeedByIdUseCase
import com.primo.domain.usecase.GetFeedListUseCase
import com.primo.domain.usecase.GetMediumFeedByIdUseCase
import com.primo.domain.usecase.GetMediumFeedListUseCase
import com.primo.domain.usecase.RefreshFeedUseCase
import com.primo.domain.usecase.RefreshMediumFeedUseCase
import org.koin.dsl.module

val domainModule = module {
    factory {
        FeedMapper()
    }
    factory {
        FeedDetailMapper()
    }
    factory<GetFeedListUseCase> {
        GetMediumFeedListUseCase(
            feedMapper = get(),
            feedRepository = get()
        )
    }
    factory<RefreshFeedUseCase> {
        RefreshMediumFeedUseCase(
            feedMapper = get(),
            feedRepository = get()
        )
    }
    factory<GetFeedByIdUseCase> {
        GetMediumFeedByIdUseCase(
            feedMapper = get(),
            feedRepository = get()
        )
    }
}