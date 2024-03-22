package com.primo.feature.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.primo.domain.entity.FeedDetail
import com.primo.feature.presentation.detail.FeedDetailScreen
import com.primo.feature.presentation.main.FeedMainScreen

enum class FeedRoute(val destination: String) {
    FEED_MAIN("feed_main"),
    FEED_DETAIL("feed_detail")
}

fun NavController.navigateToFeedDetail(feedDetail: FeedDetail, options: NavOptions? = null) =
    navigate(route = FeedRoute.FEED_DETAIL.destination, navOptions = options)

fun NavGraphBuilder.feedNavigationBuilder(navController: NavHostController) {
    composable(
        route = FeedRoute.FEED_MAIN.destination,
    ) {
        FeedMainScreen { data ->
            navController.navigateToFeedDetail(feedDetail = data)
        }
    }
    composable(
        route = FeedRoute.FEED_DETAIL.destination,
    ) {
        FeedDetailScreen(navController = navController)
    }
}