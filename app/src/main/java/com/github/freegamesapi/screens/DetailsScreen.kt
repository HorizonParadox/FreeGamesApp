package com.github.freegamesapi.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign.Companion.Justify
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.github.freegamesapi.MainViewModel
import com.github.freegamesapi.R
import com.github.freegamesapi.ui.theme.*


@Composable
fun DetailsScreen(viewModel: MainViewModel, itemId: String) {
  val context = LocalContext.current
  val currentItem = viewModel.allGames
    .observeAsState(listOf()).value
    .firstOrNull { it.id == itemId.toInt() }

  Surface(modifier = Modifier.fillMaxSize(), color = GreyBackground)
  {
    LazyColumn(modifier = Modifier.padding(vertical = 24.dp, horizontal = 16.dp)) {
      item {
        Column(
          horizontalAlignment = Alignment.CenterHorizontally
        ) {
          Image(
            modifier = Modifier
              .size(512.dp, 256.dp)
              .clip(RoundedCornerShape(64.dp)),
            painter = rememberAsyncImagePainter(currentItem?.thumbnail),
            contentDescription = null
          )
          Column(
            modifier = Modifier
              .fillMaxWidth()
              .background(GreyCard, RoundedCornerShape(12.dp))
              .padding(start = 16.dp, end = 16.dp)
          ) {
            Text(
              modifier = Modifier
                .padding(top = 16.dp)
                .align(Alignment.CenterHorizontally),
              text = currentItem?.title ?: "Some game",
              fontWeight = FontWeight.Bold,
              fontSize = 32.sp,
              color = TextColor
            )
            Row(
              modifier = Modifier
                .padding(top = 8.dp)
                .align(Alignment.End)
            ) {
              Box(
                modifier = Modifier
                  .background(TextGenreBackground, RoundedCornerShape(3.dp))
              ) {
                Text(
                  modifier = Modifier.padding(start = 3.dp, end = 3.dp),
                  text = currentItem?.genre ?: "Unknown",
                  color = GreyCard,
                  fontWeight = FontWeight.Bold,
                  fontSize = 14.sp,
                )
              }
              Box(
                modifier = Modifier
                  .padding(start = 8.dp)
                  .background(TextGenreBackground, RoundedCornerShape(3.dp))
              ) {
                Text(
                  modifier = Modifier.padding(start = 3.dp, end = 3.dp),
                  text = currentItem?.release_date ?: "Unknown",
                  color = GreyCard,
                  fontWeight = FontWeight.Bold,
                  fontSize = 14.sp,
                )
              }
              Icon(
                modifier = Modifier
                  .size(30.dp, 18.dp)
                  .align(Alignment.CenterVertically)
                  .padding(start = 8.dp)
                  .background(TextDescriptionColor, RoundedCornerShape(3.dp)),
                imageVector = if (currentItem?.platform == "PC (Windows)")
                  ImageVector.vectorResource(id = R.drawable.windows)
                else
                  ImageVector.vectorResource(id = R.drawable.browser),
                contentDescription = null,
                tint = GreyCard
              )
            }
            Row(modifier = Modifier.padding(top = 8.dp)) {
              Text(
                text = "Developer: ",
                color = TextColor,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
              )
              Text(
                text = currentItem?.developer ?: "Some developer",
                color = TextDescriptionColor,
                fontSize = 18.sp,
                maxLines = 1,
              )
            }
            Text(
              modifier = Modifier.padding(top = 8.dp),
              text = currentItem?.short_description ?: "This game has no description",
              fontSize = 18.sp,
              color = TextDescriptionColor,
              textAlign = Justify
            )
            Button(
              modifier = Modifier
                .padding(top = 16.dp, bottom = 16.dp)
                .align(Alignment.CenterHorizontally),
              colors = ButtonDefaults.buttonColors(backgroundColor = ButtonColor),
              onClick = {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(currentItem?.game_url ?: "https://http.cat/404"))
                context.startActivity(browserIntent)
              }
            ) {
              Row {
                Text(
                  text = "Play Now".uppercase(),
                  fontSize = 22.sp,
                  color = Color.White,
                  fontWeight = FontWeight.Bold,
                )
                Icon(
                  modifier = Modifier
                    .padding(start = 8.dp)
                    .size(16.dp)
                    .align(Alignment.CenterVertically),
                  imageVector = ImageVector.vectorResource(id = R.drawable.link),
                  contentDescription = null,
                  tint = Color.White
                )
              }
            }
          }
        }
      }
    }
  }
}