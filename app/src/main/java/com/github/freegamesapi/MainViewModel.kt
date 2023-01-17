package com.github.freegamesapi

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.freegamesapi.data.models.Games
import com.github.freegamesapi.data.network.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: ApiRepository): ViewModel() {
  private val _allGames =  MutableLiveData<List<Games>>()
  val allGames: LiveData<List<Games>>
    get() = _allGames

  fun getAllGames(){
    viewModelScope.launch {
      repository.getAllGames().let {
        if (it.isSuccessful){
          _allGames.postValue(it.body())
        } else{
          Log.d("checkData", "Failed to load games: ${it.errorBody()}")
        }
      }
    }
  }
}