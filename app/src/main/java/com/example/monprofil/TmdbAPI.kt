package com.example.monprofil

import retrofit2.http.GET
import retrofit2.http.Query

interface TmdbAPI {
    @GET("trending/movie/week")
    suspend fun getFilmSemaine(@Query("api_key") apikey: String) : TmdbMoviesResult

    @GET("search/movie")
    suspend fun getFilmsParMotCle(@Query("api_key") apikey: String, @Query("query") motcle: String) : TmdbResultMotCle

    @GET("trending/person/week")
    suspend fun getActorsOfTheWeek(@Query("api_key") api_key: String): TmdbActorsResult

    @GET("trending/tv/week")
    suspend fun getSeriesOfTheWeek(@Query("api_key") apiKey: String, @Query("language") language: String) : TmdbSeriesResult
}