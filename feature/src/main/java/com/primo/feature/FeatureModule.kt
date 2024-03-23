package com.primo.feature

import com.primo.feature.presentation.detail.FeedDetailViewModel
import com.primo.feature.presentation.main.FeedMainViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val featureModule = module {

    viewModelOf(::FeedMainViewModel)
    viewModelOf(::FeedDetailViewModel)

}