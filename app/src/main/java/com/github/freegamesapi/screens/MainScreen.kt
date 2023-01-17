package com.github.freegamesapi.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.github.freegamesapi.MainViewModel
import com.github.freegamesapi.data.models.Games
import com.github.freegamesapi.navigation.Screens

@Composable
fun MainScreen(navController: NavController, viewModel: MainViewModel){
  val allGames = viewModel.allGames.observeAsState(listOf()).value

  Surface(modifier = Modifier.fillMaxSize()) {
    LazyColumn(
      modifier = Modifier.padding(20.dp)
    ){
      items(allGames){ item->
        GamesItem(item = item, navController= navController)
      }
    }
  }
}

@Composable
fun GamesItem(item: Games, navController: NavController){
  Card(
    modifier = Modifier
      .padding(top = 8.dp)
      .clickable {
        navController.navigate(Screens.Details.route + "/${item.id}")
      }
  ) {
    Row(
      modifier = Modifier
        .fillMaxSize()
        .padding(vertical = 16.dp)
    )
    {
      Image(
        modifier = Modifier.size(128.dp),
        painter = rememberAsyncImagePainter(item.thumbnail),
        contentDescription = null
      )
      Column(
        modifier = Modifier.padding(start = 16.dp)
      ) {
        Text(
          text = item.title,
          fontSize = 24.sp,
          fontWeight = FontWeight.Bold
        )
        Row {
          Text(
            text = "Genre: ",
            fontWeight = FontWeight.Bold
          )
          Text(
            text = item.genre
          )
        }
        Row {
          Text(
            text = "Platform: ",
            fontWeight = FontWeight.Bold
          )
          Text(
            text = item.platform
          )
        }
        Row {
          Text(
            text = "Release date: ",
            fontWeight = FontWeight.Bold
          )
          Text(
            text = item.release_date
          )
        }
        Row {
          Text(
            text = "Developer: ",
            fontWeight = FontWeight.Bold
          )
          Text(
            text = item.developer
          )
        }
      }
    }
  }
}