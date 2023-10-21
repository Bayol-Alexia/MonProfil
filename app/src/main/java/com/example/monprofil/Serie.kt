package com.example.monprofil

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.BottomNavigation
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SerieScreen(
    navController: NavController,
    windowClass: WindowSizeClass,
    viewModel: MainViewModel
) {
    Scaffold(
        topBar = {
            var isSearching by remember { mutableStateOf(false) }
            var searchText by remember { mutableStateOf("") }

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
                                navController.navigate("Profil")
                            }
                        }
                    ) {
                        Icon(
                            imageVector = if (isSearching) Icons.Default.ArrowBack else Icons.Filled.Home,
                            contentDescription = if (isSearching) "Retour" else "Profil"
                        )
                    }
                },
                actions = {
                    Spacer(modifier = Modifier.width(16.dp))
                    if (isSearching) {
                        TextField(
                            value = searchText,
                            onValueChange = { searchText = it },
                            modifier = Modifier
                                .width(350.dp) // Largeur fixe du champ de texte
                                .padding(end = 16.dp),
                            textStyle = TextStyle(color = Color.White),
                            singleLine = true,
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Text,
                                imeAction = ImeAction.Done
                            ),
                            keyboardActions = KeyboardActions(
                                onDone = {
                                    //FilmsMotCle(navController, windowClass, viewModel)
                                    isSearching = false
                                }
                            )
                        )
                    } else {
                        IconButton(onClick = { isSearching = !isSearching }) {
                            Icon(imageVector = Icons.Default.Search, contentDescription = "Recherche")
                        }
                    }
                }
            )
        },

        bottomBar = {

            BottomNavigation (
                modifier = Modifier.background(color = Color.Red)
            ) {

                var selectedItem by remember { mutableIntStateOf(1) }
                val items = listOf("Films", "Séries", "Acteurs")
                val icons = listOf(
                    painterResource(id = R.drawable.icon_film),
                    painterResource(id = R.drawable.icon_serie),
                    painterResource(id = R.drawable.icon_acteur)
                )
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        icon = { Image(painter = icons[index], contentDescription = "Icône") },
                        label = { Text(item) },
                        selected = selectedItem == index,
                        onClick = {
                            when (index) {
                                0 -> navController.navigate("Films")
                                1 -> navController.navigate("Series")
                                2 -> navController.navigate("Acteurs")
                            }
                        }
                    )
                }

            }
        }
    ) {
        SerieWeek(navController, windowClass, viewModel)
    }
}

@Composable
fun SerieWeek(navController: NavController, windowClass: WindowSizeClass,serieViewModel: MainViewModel) {

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Contenu principal
        when (windowClass.widthSizeClass) {
            WindowWidthSizeClass.Compact -> {
                val series by serieViewModel.series.collectAsState()

                LazyVerticalGrid(columns = GridCells.Fixed(2)) {
                    items(series) { serie ->
                        val imageUrl = "https://image.tmdb.org/t/p/w780${serie.poster_path}"
                        Column (
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Image(
                                painter = rememberAsyncImagePainter(
                                    ImageRequest.Builder(LocalContext.current).data(data = imageUrl)
                                        .apply(block = fun ImageRequest.Builder.() {
                                            crossfade(true)
                                            size(450, 500)
                                        }).build()
                                ),
                                contentDescription = "Image acteur",
                                modifier = Modifier
                                    .padding(start = 5.dp, end = 5.dp)
                                    .width(200.dp)
                                    .height(300.dp)
                            )
                            Spacer(Modifier.size(30.dp))
                        }
                    }
                }
            }
        }
    }
}