package com.example.monprofil

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.BottomNavigation
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilmDetailScreen(
    navController: NavController,
    windowClass: WindowSizeClass,
    movieID: String,
    viewModel: MainViewModel
) {
    Scaffold(
        topBar = {
            var isSearching by remember { mutableStateOf(false) }

            TopAppBar(

                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = Color.Red,
                    titleContentColor = Color.White,

                    ),
                title = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = if (isSearching) "Recherche" else "CineStream",
                            modifier = Modifier.align(Alignment.CenterVertically)
                        )
                    }
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            if (isSearching) {
                                isSearching = false
                            } else {
                                navController.navigate("Films")
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = if (isSearching) "Retour" else "Films"
                        )
                    }
                },
            )
        },

        bottomBar = {
            BottomNavigation(
                backgroundColor = Color.Red,
                modifier = Modifier.height(80.dp)
            ) {
                var selectedItem by remember { mutableStateOf(0) }
                val items = listOf("Films", "Séries", "Acteurs")
                val icons = listOf(
                    painterResource(id = R.drawable.icon_film),
                    painterResource(id = R.drawable.icon_serie),
                    painterResource(id = R.drawable.icon_acteur)
                )
                items.forEachIndexed { index, item ->
                    val destination = when (index) {
                        0 -> "Films"
                        1 -> "Series"
                        2 -> "Acteurs"
                        else -> "fallback_destination"
                    }

                    NavigationBarItem(
                        icon = {
                            Image(
                                painter = icons[index],
                                contentDescription = "Icône",
                                modifier = Modifier.size(30.dp)
                            )
                        },
                        label = { Text(item) },
                        selected = selectedItem == index,
                        onClick = {
                            selectedItem = index
                            navController.navigate(route = destination)
                        }
                    )
                }
            }
        }
    ) {
        FilmDetail(navController, windowClass, movieID, viewModel, it)
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FilmDetail(
    navController: NavController,
    windowClass: WindowSizeClass,
    movieID: String,
    viewModel: MainViewModel,
    padding: PaddingValues
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
            .background(Color.Black)
    ) {
        val movie by viewModel.movie.collectAsState()
        LaunchedEffect(true) {
            viewModel.InfoMovie(movieID)
        }

        LazyColumn {
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
                                    size(600, 600)
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
                            fontSize = 30.sp,
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
                        color = Color.Red,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(top = 15.dp, end = 15.dp)
                    )
                    Text(
                        text = movie.overview,
                        color = Color.White,
                        textAlign = TextAlign.Justify,
                        modifier = Modifier.padding(top = 15.dp, end = 15.dp),
                    )
                }
            }
            item {
                Text(
                    text = "Casting",
                    color = Color.Red,
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
                                        onClick = { navController.navigate("ActeursDetail/${cast.id}") },
                                        modifier = Modifier
                                            .size(120.dp)
                                            .clip(CircleShape),
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


@Composable
fun getGenres(genres: List<Genre>): String {
    return genres.joinToString(", ") { it.name }
}

