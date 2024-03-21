package com.primo.feed

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.primo.feature.navigation.FeedRoute
import com.primo.feature.navigation.feedNavigationBuilder

@Composable
fun MainNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = FeedRoute.FEED_MAIN.destination,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        this.feedNavigationBuilder(navController = navController)
    }
}