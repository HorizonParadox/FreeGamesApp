package com.github.freegamesapi.data.network

import com.github.freegamesapi.data.models.Games
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
  @GET("/api/games")
  suspend fun getAllGames(): Response<List<Games>>
}