package com.github.freegamesapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.github.freegamesapi.navigation.SetupNavHost
import com.github.freegamesapi.ui.theme.FreeGamesAPITheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FreeGamesAPITheme {
                val navController = rememberNavController()
                SetupNavHost(navController = navController)
            }
        }
    }
}