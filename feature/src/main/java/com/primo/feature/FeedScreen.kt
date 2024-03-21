package com.primo.feature

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.primo.feature.theme.PrimoFeedTheme

@Composable
fun FeedScreen() {
    PrimoFeedTheme {
        Scaffold {
            Column(modifier = Modifier.padding(it)) { }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FeedPreview() {

}


