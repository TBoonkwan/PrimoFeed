package com.primo.feature.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.primo.domain.entity.FeedDetail
import com.primo.feature.presentation.detail.FeedDetailScreen
import com.primo.feature.presentation.main.FeedMainScreen

enum class FeedRoute(val destination: String) {
    FEED("feed"),
}

const val FEED_PARAMS = "id"
const val FEED_ID = "feedId"

fun NavController.navigateToFeedDetail(feedDetail: FeedDetail, options: NavOptions? = null) =
    navigate(
        route = "${FeedRoute.FEED.destination}?${FEED_PARAMS}=${feedDetail.id}",
        navOptions = options
    )

fun NavGraphBuilder.feedScreen(navController: NavHostController) {
    composable(
        route = FeedRoute.FEED.destination,
    ) {
        FeedMainScreen { data ->
            navController.navigateToFeedDetail(feedDetail = data)
        }
    }

    composable(
        route = "${FeedRoute.FEED.destination}?${FEED_PARAMS}={${FEED_ID}}",
        arguments = listOf(navArgument(FEED_ID) {
            type = NavType.StringType
        })
    ) {
        val feedId = it.arguments?.getString(FEED_ID)
        FeedDetailScreen(feedId = feedId.orEmpty()) {
            navController.popBackStack()
        }
    }
}