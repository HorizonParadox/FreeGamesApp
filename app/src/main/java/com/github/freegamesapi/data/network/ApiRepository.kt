package com.github.freegamesapi.data.network

import javax.inject.Inject

class ApiRepository @Inject constructor(private val apiService: ApiService) {
  suspend fun getAllGames() = apiService.getAllGames()
}