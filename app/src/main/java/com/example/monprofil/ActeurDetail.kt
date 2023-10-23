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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BottomNavigation
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.request.ImageRequest

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActeurDetailScreen(
    navController: NavController,
    windowClass: WindowSizeClass,
    acteurID: String,
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
                                navController.navigate("Acteurs")
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = if (isSearching) "Retour" else "Acteurs"
                        )
                    }
                },
            )},

        bottomBar = {

            BottomNavigation(
                backgroundColor = Color.Red,
                modifier = Modifier.height(80.dp)
            ) {
                var selectedItem by remember { mutableStateOf(2) }
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
                        icon = { Image(painter = icons[index], contentDescription = "Icône", modifier = Modifier.size(30.dp))},
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
        ActeurDetail(navController, windowClass, acteurID, viewModel, it)
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ActeurDetail(navController: NavController,windowClass: WindowSizeClass, acteurID: String, actorViewModel: MainViewModel, padding:PaddingValues) {

    Box(
        modifier = Modifier.fillMaxSize().padding(padding)
    ) {

        val acteur by actorViewModel.acteur.collectAsState()

        LaunchedEffect(true) {
            actorViewModel.InfoActeur(acteurID)
        }

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = CenterHorizontally,
            modifier = Modifier.verticalScroll(rememberScrollState())
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Image(
                    painter = rememberAsyncImagePainter(
                        model = "https://image.tmdb.org/t/p/h632${acteur.profile_path}"
                    ),
                    contentDescription = "Image acteur ${acteur.name}",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(200.dp)
                        .padding(start = 25.dp, end = 10.dp, top = 5.dp)
                        .clip(shape = RoundedCornerShape(16.dp))
                )
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = CenterHorizontally,
                    modifier = Modifier.padding(top = 15.dp, start = 16.dp, end = 16.dp)
                ) {
                    Text(
                        text = acteur.name,
                        fontWeight = FontWeight.Bold,
                        fontSize = 25.sp,
                        color = Color.Black,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    )
                    val genderText = if (acteur.gender == 1) "Femme" else "Homme"
                    Text(
                        text = "$genderText",
                        color = Color.Black,
                        fontSize = 15.sp
                    )

                    if (acteur.known_for_department != "") {
                        Text(
                            text = "${acteur.known_for_department}",
                            color = Color.Black,
                            fontSize = 15.sp

                        )
                    }
                }
            }
            Spacer(Modifier.size(10.dp))

            val isBirthdayValid =
                acteur.birthday?.matches("^\\d{4}-\\d{2}-\\d{2}\$".toRegex()) == true
            if (acteur.place_of_birth != "" && isBirthdayValid) {
                Text(
                    text = "Lieu de naissance : ${acteur.place_of_birth}",
                    fontStyle = FontStyle.Italic,
                    color = Color.Black,
                )
            }



            Column(
                modifier = Modifier.padding(start = 10.dp),
                horizontalAlignment = CenterHorizontally,
            ) {
                if (acteur.biography != "") {
                    Text(
                        text = "Biographie",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(top = 15.dp, start = 15.dp)
                    )
                    Text(
                        text = acteur.biography,
                        color = Color.Black,
                        textAlign = TextAlign.Justify,
                        modifier = Modifier.padding(top = 15.dp, end = 15.dp)
                    )
                }
            }
        }
    }
}



