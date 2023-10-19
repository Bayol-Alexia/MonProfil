package com.example.monprofil

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainViewModel : ViewModel() {
    val movies = MutableStateFlow<List<MovieWeek>>(listOf())

    val apikey = "7745960998b08adddf196d8d124b9ae0"

    val service =Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(TmdbAPI::class.java)

    fun searchMovies(motcle: String){
        viewModelScope.launch {
            movies.value = service.getFilmsParMotCle(apikey, motcle).results
            movies.value = service.getFilmSemaine(apikey).results
        }
    }
}