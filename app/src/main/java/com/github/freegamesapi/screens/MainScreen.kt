package com.github.freegamesapi.screens

import android.util.Log
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.github.freegamesapi.MainViewModel
import com.github.freegamesapi.data.models.Games

@Composable
fun MainScreen(navController: NavController, viewModel: MainViewModel){
  val allGames = viewModel.allGames.observeAsState(listOf()).value
  allGames.forEach { Log.d("checkData", "ID: ${it.id} name ${it.title}") }
  Surface(modifier = Modifier.fillMaxSize()) {
    LazyColumn(){
      items(allGames){ item->
        GamesItem(item = item)
      }
    }
  }
}

@Composable
fun GamesItem(item: Games){
  Row(modifier = Modifier.fillMaxSize()) {
    Text(text = item.id.toString())
    Text(text = item.title)
  }
}