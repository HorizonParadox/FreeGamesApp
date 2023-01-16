package com.github.freegamesapi.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.github.freegamesapi.screens.MainScreen
import com.github.freegamesapi.screens.SplashScreen
import com.github.freegamesapi.utils.Constants

sealed class Screens(val route: String){
  object Splash: Screens(route = Constants.Screens.SPLASH_SCREEN)
  object Main: Screens(route = Constants.Screens.MAIN_SCREEN)
  object Details: Screens(route = Constants.Screens.DETAILS_SCREEN)
}

@Composable
fun SetupNavHost(navController: NavHostController){
  NavHost(
    navController = navController,
    startDestination = Screens.Splash.route
  ){

    composable(route = Screens.Splash.route){
      SplashScreen(navController = navController)
    }

    composable(route = Screens.Main.route){
      MainScreen()
    }

    composable(route = Screens.Details.route){

    }

  }
}