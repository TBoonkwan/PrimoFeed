package com.primo.data

import com.primo.data.repository.FeedRepository
import com.primo.data.repository.MediumFeedRepository
import com.primo.data.source.local.LocalFeedDataSource
import com.primo.data.source.remote.RemoteFeedDataSource
import org.koin.dsl.module

val dataModule = module {
    single { LocalFeedDataSource() }
    single { RemoteFeedDataSource() }

    single<FeedRepository> {
        MediumFeedRepository(
            localFeedDataSource = get(),
            remoteFeedDataSource = get()
        )
    }
}