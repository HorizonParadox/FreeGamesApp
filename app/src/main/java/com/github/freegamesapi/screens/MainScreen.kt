package com.github.freegamesapi.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.github.freegamesapi.MainViewModel
import com.github.freegamesapi.data.models.Games
import com.github.freegamesapi.navigation.Screens
import com.github.freegamesapi.ui.theme.*

@Composable
fun MainScreen(navController: NavController, viewModel: MainViewModel){
  val allGames = viewModel.allGames.observeAsState(listOf()).value

  Surface(modifier = Modifier.fillMaxSize(), color = GreyBackground) {
    LazyColumn(
      modifier = Modifier.padding(20.dp),
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
      .clip(RoundedCornerShape(12.dp))
      .clickable {
        navController.navigate(Screens.Details.route + "/${item.id}")
      },
    backgroundColor = GreyCard
  ) {
    Row(
      modifier = Modifier
        .fillMaxSize()
        .padding(vertical = 16.dp)
    )
    {
      Image(
        modifier = Modifier
          .size(width = 128.dp, height = 80.dp)
          .align(Alignment.CenterVertically)
          .padding(start = 8.dp)
          .clip(RoundedCornerShape(32.dp)),
        painter = rememberAsyncImagePainter(item.thumbnail),
        contentDescription = null
      )
      Column(
        modifier = Modifier.padding(start = 16.dp, end = 8.dp)
      ) {
        Text(
          text = item.title,
          fontSize = 24.sp,
          fontWeight = FontWeight.Bold,
          color = TextColor
        )
        Row{
          Text(
            text = item.short_description,
            color = TextDescriptionColor,
            maxLines = 1
          )
        }
        Row(modifier = Modifier
          .padding(top = 8.dp)
          .align(Alignment.End)) {
          Box(
            modifier = Modifier
              .background(TextGenreBackground, RoundedCornerShape(3.dp))
          ){
            Text(
              modifier = Modifier.padding(start = 3.dp, end = 3.dp),
              text = item.genre,
              color = GreyCard,
              fontWeight = FontWeight.Bold,
              fontSize = 14.sp,
            )
          }
          Box(
            modifier = Modifier
              .padding(start = 8.dp)
              .background(TextGenreBackground, RoundedCornerShape(3.dp))
          ){
            Text(
              modifier = Modifier.padding(start = 3.dp, end = 3.dp),
              text = item.release_date,
              color = GreyCard,
              fontWeight = FontWeight.Bold,
              fontSize = 14.sp,
            )
          }
          Icon(modifier = Modifier
            .size(30.dp, 18.dp)
            .align(Alignment.CenterVertically)
            .padding(start = 8.dp)
            .background(TextDescriptionColor, RoundedCornerShape(3.dp)),
            imageVector = if (item.platform == "PC (Windows)")
              ImageVector.vectorResource(id = com.github.freegamesapi.R.drawable.windows)
            else
              ImageVector.vectorResource(id = com.github.freegamesapi.R.drawable.browser),
            contentDescription = null,
          tint = GreyCard)
        }
      }
    }
  }
}