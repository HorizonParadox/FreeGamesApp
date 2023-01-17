package com.github.freegamesapi.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.github.freegamesapi.MainViewModel

@Composable
fun DetailsScreen(viewModel: MainViewModel, itemId: String) {
  val currentItem = viewModel.allGames
    .observeAsState(listOf()).value
    .firstOrNull{ it.id == itemId.toInt() }
  
  Surface(
    modifier = Modifier
      .fillMaxSize()
      .padding(vertical = 24.dp, horizontal = 8.dp)
  ) {
    LazyColumn(){
      item{
        Column(
          horizontalAlignment = Alignment.CenterHorizontally
        ) {
          Image(
            modifier = Modifier.size(365.dp),
            painter = rememberAsyncImagePainter(currentItem?.thumbnail),
            contentDescription = null
          )
          Text(
            modifier = Modifier.padding(top = 16.dp),
            text = currentItem?.title ?: "",
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp,
          )
          Row(
            modifier = Modifier.padding(top = 8.dp)
          ) {
            Text(
              text = currentItem?.short_description ?: "",
              fontSize = 18.sp
            )
          }
          Row(
            modifier = Modifier.padding(top = 8.dp)
          ) {
            Text(
              text = "URL: ",
              fontWeight = FontWeight.Bold,
              fontSize = 18.sp
            )
            Text(
              text = currentItem?.game_url ?: "",
              fontSize = 18.sp
            )
          }
        }
      }
    }
  }
}