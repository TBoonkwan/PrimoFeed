package com.primo.feature.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.primo.feature.theme.AppTypography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeedDetailScreen(navController: NavHostController) {
    Scaffold(topBar = {
        TopAppBar(
            navigationIcon = {
                IconButton(onClick = {
                    navController.popBackStack()
                }) {
                    Icon(
                        painter = painterResource(com.google.android.material.R.drawable.ic_arrow_back_black_24),
                        contentDescription = "ic_arrow_back_black_24"
                    )
                }
            },
            title = {
                Text(
                    text = "feedUIModel.title", style = AppTypography.headlineLarge
                )
            },
        )
    }) {
        Column(modifier = Modifier.padding(it)) {

        }
    }
}

@Preview(showBackground = true)
@Composable
fun FeedDetailPreview() {

}


