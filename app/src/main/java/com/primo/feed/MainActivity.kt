package com.primo.feed

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.primo.common_ui.theme.PrimoFeedTheme
import org.koin.compose.KoinContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            KoinContext {
                PrimoFeedTheme {
                    MainNavHost(navController = navController)
                }
            }
        }
    }
}