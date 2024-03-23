package com.primo.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class FeedDetailUIModel(
    var profile: FeedProfile,
    var feedDetail: FeedDetail,
) : Parcelable