package com.github.freegamesapi.screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.github.freegamesapi.MainViewModel
import com.github.freegamesapi.navigation.Screens
import com.github.freegamesapi.ui.theme.GreyBackground
import com.github.freegamesapi.ui.theme.TextColor
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController, viewModel: MainViewModel){
  var startAnimate by remember {
    mutableStateOf(false)
  }
  val alphaAnimation = animateFloatAsState(
    targetValue = if (startAnimate) 1f else 0f,
    animationSpec = tween(durationMillis = 3000)
  )
  LaunchedEffect(key1 = true){
    startAnimate = true
    viewModel.getAllGames()
    delay(4000)
    navController.navigate(Screens.Main.route)
  }
  Splash(alpha = alphaAnimation.value)
}

@Composable
fun Splash(alpha: Float){
  Box(
    modifier = Modifier.fillMaxSize().background(GreyBackground),
    contentAlignment = Alignment.Center
  ){
    Icon(
      modifier = Modifier
        .size(128.dp)
        .alpha(alpha)
        .padding(8.dp),
      imageVector = ImageVector.vectorResource(id = com.github.freegamesapi.R.drawable.gamepad_solid),
      contentDescription = null,
      tint = TextColor
    )
  }
}