package com.example.monprofil

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.SavedStateHandle
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {
    val movies = MutableStateFlow<List<TmdbMovies>>(listOf())
    val acteurs = MutableStateFlow<List<TmdbActors>>(listOf())
    val series = MutableStateFlow<List<TmdbSeries>>(listOf())
    val movie = MutableStateFlow<MoviesInfos>(MoviesInfos())
    val serie = MutableStateFlow<SeriesInfos>(SeriesInfos())
    val acteur = MutableStateFlow<ActeursInfos>(ActeursInfos())

    val apikey = "7745960998b08adddf196d8d124b9ae0"

    val service =Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(TmdbAPI::class.java)

    fun FilmsRecherche(motcle: String){
        viewModelScope.launch {
            movies.value = service.getFilmsParMotCle(apikey, motcle).results
        }
    }
    fun SeriesRecherche(motcle: String){
        viewModelScope.launch {
            series.value = service.getSerieParMotCle(apikey, motcle).results
        }
    }
    fun ActeursRecherche(motcle: String){
        viewModelScope.launch {
            acteurs.value = service.getActeurParMotCle(apikey, motcle).results
        }
    }

    fun FilmsWeek(){
        viewModelScope.launch {
        movies.value = service.getFilmSemaine(apikey).results
        }
    }
    fun SeriesWeek() {
        viewModelScope.launch {
            series.value = service.getSeriesOfTheWeek(apikey).results
        }
    }

    fun ActeurWeek() {
        viewModelScope.launch {
            acteurs.value = service.getActorsOfTheWeek(apikey).results
        }
    }

    fun InfoMovie(movieID: String) {
        viewModelScope.launch {
            movie.value = service.getFilmDetail(movieID, apikey)
        }
    }

    fun InfoSerie(serieID: String) {
        viewModelScope.launch {
            serie.value = service.getSerieDetail(serieID, apikey)
        }
    }

    fun InfoActeur(acteurID: String) {
        viewModelScope.launch {
            acteur.value = service.getActeurDetail(acteurID, apikey)
        }
    }
}