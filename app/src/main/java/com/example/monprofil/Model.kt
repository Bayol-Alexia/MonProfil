package com.example.monprofil
data class TmdbResultMotCle(
    val page: Int,
    val results: List<TmdbMovie>,
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
    val page: Int = 0,
    val results: List<TmdbMovie> = listOf(),
    val total_pages: Int = 0,
    val total_results: Int = 0
)

data class TmdbMovie(
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
    val vote_count: Int = 0
)

data class TmdbSeriesResult(
    val page: Int = 0,
    val results: List<TmdbSerie> = listOf(),
    val total_pages: Int = 0,
    val total_results: Int = 0
)

data class TmdbSerie(
    val adult: Boolean = false,
    val backdrop_path: String = "",
    val first_air_date: String = "",
    val genre_ids: List<Int> = listOf(),
    val id: String = "",
    val media_type: String = "",
    val name: String = "",
    val origin_country: List<String> = listOf(),
    val original_language: String = "",
    val original_name: String = "",
    val overview: String = "",
    val popularity: Double = 0.0,
    val poster_path: String = "",
    val vote_average: Double = 0.0,
    val vote_count: Int = 0
)

data class TmdbActorsResult(
    val page: Int = 0,
    val results: List<TmdbActor> = listOf(),
    val total_pages: Int = 0,
    val total_results: Int = 0
)

data class TmdbActor(
    val adult: Boolean = false,
    val gender: Int = 0,
    val id: String = "",
    val known_for: List<KnownForActorResult> = listOf(),
    val known_for_department: String = "",
    val media_type: String = "",
    val name: String = "",
    val original_name: String = "",
    val popularity: Double = 0.0,
    val profile_path: String = ""
)

data class KnownForActorResult(
    val adult: Boolean = false,
    val backdrop_path: String = "",
    val genre_ids: List<Int> = listOf(),
    val id: String = "",
    val media_type: String = "",
    val original_language: String = "",
    val original_title: String = "",
    val overview: String = "",
    val popularity: Double = 0.0,
    val poster_path: String = "",
    val release_date: String = "",
    val title: String = "",
    val video: Boolean = false,
    val vote_average: Double = 0.0,
    val vote_count: Int = 0
)


