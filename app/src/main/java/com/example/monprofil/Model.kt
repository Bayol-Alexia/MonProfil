package com.example.monprofil
data class Genre(
    val id: String,
    val name: String
)
data class Credits(
    val cast: List<Cast> = listOf(),
    val crew: List<Crew> = listOf()
)
data class Cast(
    val adult: Boolean,
    val cast_id: String,
    val character: String,
    val credit_id: String,
    val gender: Int,
    val id: String,
    val known_for_department: String,
    val name: String,
    val order: Int,
    val original_name: String,
    val popularity: Double,
    val profile_path: String
)
data class Crew(
    val adult: Boolean,
    val credit_id: String,
    val department: String,
    val gender: Int,
    val id: String,
    val job: String,
    val known_for_department: String,
    val name: String,
    val original_name: String,
    val popularity: Double,
    val profile_path: String
)




data class TmdbResultMotCle(
    val page: Int,
    val results: List<TmdbMovies>,
    val total_pages: Int,
    val total_results: Int
)
data class MovieMotCle(
    val adult: Boolean,
    val backdrop_path: String,
    val genre_ids: List<Int>,
    val id: Int,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
)



data class TmdbMoviesResult(
    val page: Int,
    val results: List<TmdbMovies>,
    val total_pages: Int,
    val total_results: Int
)
data class TmdbMovies(
    val adult: Boolean,
    val backdrop_path: String,
    val genre_ids: List<Int>,
    val id: Int,
    val media_type: String,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
)
data class MoviesInfos(
    val adult: Boolean = false,
    val backdrop_path: String = "",
    val genre_ids: List<Int> = listOf(),
    val id: String = "",
    val original_language: String = "",
    val original_title: String = "",
    val overview: String = "",
    val popularity: Double = 0.0,
    val poster_path: String = "",
    val release_date: String = "",
    val title: String = "",
    val video: Boolean = false,
    val vote_average: Double = 0.0,
    val vote_count: Int = 0,
    val genres: List<Genre> = listOf(),
    val credits: Credits = Credits()
)



data class TmdbSeriesResult(
    val page: Int,
    val results: List<TmdbSeries>,
    val total_pages: Int,
    val total_results: Int
)
data class TmdbSeries(
    val adult: Boolean,
    val backdrop_path: String,
    val first_air_date: String,
    val genre_ids: List<Int>,
    val id: Int,
    val media_type: String,
    val name: String,
    val origin_country: List<String>,
    val original_language: String,
    val original_name: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val vote_average: Double,
    val vote_count: Int
)




data class TmdbActorsResult(
    val page: Int,
    val results: List<TmdbActors>,
    val total_pages: Int,
    val total_results: Int
)
data class TmdbActors(
    val adult: Boolean,
    val gender: Int,
    val id: Int,
    val known_for: List<KnownForActorsResult>,
    val known_for_department: String,
    val media_type: String,
    val name: String,
    val original_name: String,
    val popularity: Double,
    val profile_path: String
)
data class KnownForActorsResult(
    val adult: Boolean,
    val backdrop_path: String,
    val genre_ids: List<Int>,
    val id: Int,
    val media_type: String,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
)

