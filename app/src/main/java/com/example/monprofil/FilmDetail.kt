package com.example.monprofil

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilmDetail(navController: NavController,windowSizeClass: WindowSizeClass, movieID: String, viewModel: MainViewModel){
    val movie by viewModel.movie.collectAsState()

    if (movie.title.isEmpty()) {
        viewModel.InfoMovie()
    }

    if (movie.title.isNotEmpty()){
        LazyColumn {
            item {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(
                            ImageRequest.Builder(LocalContext.current)
                                .data(data = "https://image.tmdb.org/t/p/w1280${movie.backdrop_path}")
                                .apply<ImageRequest.Builder>(block = fun ImageRequest.Builder.() {
                                    crossfade(true)
                                    size(700, 700)
                                }).build()
                        ),
                        contentDescription = "Image du film ${movie.title}",
                    )
                }
            }

            item {
                Row(
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(
                            ImageRequest.Builder(LocalContext.current)
                                .data(data = "https://image.tmdb.org/t/p/w1280${movie.poster_path}")
                                .apply(block = fun ImageRequest.Builder.() {
                                    crossfade(true)
                                    size(400, 400)
                                }).build()
                        ),
                        contentDescription = "Image film ${movie.title}",
                        modifier = Modifier
                            .offset(y = (-30).dp)
                            .padding(start = 25.dp, end = 10.dp, top = 5.dp)
                            .clip(shape = RoundedCornerShape(16.dp))
                    )
                    Column(
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(start = 20.dp, end = 15.dp)
                    ) {
                        Text(
                            text = movie.title,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold,
                            fontSize = 25.sp,
                            modifier = Modifier.padding(vertical = 10.dp),
                            color = Color.White
                        )
                        Text(
                            text = getGenres(movie.genres),
                            textAlign = TextAlign.Center,
                            fontStyle = FontStyle.Italic,
                            color = Color.White,
                            modifier = Modifier.padding(top = 10.dp)
                        )
                    }
                }
            }
            item {
                Column(
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier.padding(start = 10.dp)
                ) {
                    Text(
                        text = "Synopsis",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(top = 15.dp, end = 15.dp)
                    )
                    Text(
                        text = movie.overview,
                        textAlign = TextAlign.Justify,
                        modifier = Modifier.padding(top = 15.dp, end = 15.dp),
                        color = Color.White
                    )
                }
            }

            if (movie.credits.cast.isNotEmpty()) {
                item {
                    Text(
                        text = "Casting",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(top = 15.dp, start = 10.dp)
                    )
                }
                item {
                    LazyRow(
                        content = {
                            items(movie.credits.cast.take(10)) { cast ->
                                Box(
                                    modifier = Modifier.padding(16.dp)
                                ) {
                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        FloatingActionButton(
                                            onClick = { navController.navigate("InfosActeurs/${cast.id}") },
                                            modifier = Modifier.size(120.dp).clip(CircleShape),
                                        ) {
                                            Image(
                                                painter = rememberAsyncImagePainter(
                                                    ImageRequest.Builder(
                                                        LocalContext.current
                                                    )
                                                        .data(data = "https://image.tmdb.org/t/p/w780${cast.profile_path}")
                                                        .apply(block = fun ImageRequest.Builder.() {
                                                            crossfade(true)
                                                            size(350, 400)
                                                        }).build()
                                                ),
                                                contentDescription = "Image acteur ${cast.name}",
                                                contentScale = ContentScale.Crop,
                                                modifier = Modifier.fillMaxSize()
                                            )
                                        }
                                        Text(
                                            text = cast.name,
                                            fontWeight = FontWeight.Bold,
                                            color = Color.White,
                                            fontSize = 15.sp,
                                            modifier = Modifier.padding(top = 8.dp)
                                        )
                                    }
                                }
                            }
                        }
                    )
                }
            }
    }
        }
    }



@Composable
fun getGenres(genres: List<Genre>): String {
    return genres.joinToString(", ") { it.name }
}

