package com.example.monprofil

import retrofit2.http.GET
import retrofit2.http.Query

interface TmdbAPI {
    @GET("trending/movie/week")
    suspend fun getFilmSemaine(@Query("api_key") apikey: String) : TmdbResultWeek

    @GET("search/movie")
    suspend fun getFilmsParMotCle(@Query("api_key") apikey: String, @Query("query") motcle: String) : TmdbResultMotCle
}