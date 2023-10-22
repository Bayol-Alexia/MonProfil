package com.example.monprofil

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbAPI {


    //Films
    @GET("trending/movie/week")
    suspend fun getFilmSemaine(@Query("api_key") apikey: String) : TmdbMoviesResult

    @GET("movie/{movie_id}?append_to_response=credits")
    suspend fun getFilmDetail(@Path("movie_id") movieID: String, @Query("api_key") apikey: String): MoviesInfos

    @GET("search/movie")
    suspend fun getFilmsParMotCle(@Query("api_key") apikey: String, @Query("query") motcle: String) : TmdbResultMotCle



    //Acteurs
    @GET("trending/person/week")
    suspend fun getActorsOfTheWeek(@Query("api_key") apikey: String): TmdbActorsResult

    @GET("person/{person_id}?append_to_response=credits")
    suspend fun getActeurDetail(@Path("person_id") acteurID: String, @Query("api_key") apiKey: String) : ActeursInfos



    //Series
    @GET("trending/tv/week")
    suspend fun getSeriesOfTheWeek(@Query("api_key") apikey: String) : TmdbSeriesResult

    @GET("tv/{serie_id}?append_to_response=credits")
    suspend fun getSerieDetail(@Path("serie_id") serieID: String, @Query("api_key") apikey: String): SeriesInfos

}