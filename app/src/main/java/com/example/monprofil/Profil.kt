package com.example.monprofil

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@Composable
fun Profil(navController: NavController, windowClass: WindowSizeClass) {
    when (windowClass.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black)
            ) {
                Spacer(Modifier.size(35.dp))
                Image(
                    painterResource(id = R.drawable.alexia_bayol),
                    contentDescription = "C'est Alex",
                    Modifier
                        .clip(CircleShape)
                        .size(200.dp),
                )
                Spacer(Modifier.size(25.dp))
                Text(
                    text = "Alexia Bayol",
                    fontSize = 50.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Red
                )
                Spacer(Modifier.size(15.dp))
                Text(
                    text = "Elève ingénieur ISIS en FIE4",
                    fontSize = 30.sp,
                    fontStyle = FontStyle.Italic,
                    color = Color.White
                )
                Spacer(Modifier.size(60.dp))
                Row() {
                    Image(
                        painterResource(id = R.drawable.mail),
                        contentDescription = "C'est le mail",
                        Modifier.size(30.dp)
                    )
                    Text(
                        text = "  alexia.bayol@etud.univ-jfc.fr",
                        fontSize = 25.sp,
                        color = Color.White
                    )
                    Spacer(Modifier.size(50.dp))
                }
                Row() {
                    Image(
                        painterResource(id = R.drawable.instagram),
                        contentDescription = "C'est l'insta",
                        Modifier.size(30.dp)
                    )
                    Text(
                        text = "  alexiabyl",
                        fontSize = 25.sp,
                        color = Color.White
                    )
                    Spacer(Modifier.size(70.dp))
                }
                Button(
                    onClick = { navController.navigate("Films") },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
                )
                {
                    Text(
                        text = "Démarrer",
                        color = Color.White
                    )
                }
            }

        }

        else -> {
            Row(modifier = Modifier
                .background(Color.Black)
                .fillMaxSize()) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        painterResource(id = R.drawable.alexia_bayol),
                        contentDescription = "C'est Alex",
                        Modifier.clip(CircleShape),
                    )
                    Text(
                        text = "Alexia Bayol",
                        fontSize = 60.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Red
                    )
                    Spacer(Modifier.size(15.dp))
                    Text(
                        text = "Elève ingénieur ISIS en FIE4",
                        fontSize = 30.sp,
                        color = Color.White,
                        fontStyle = FontStyle.Italic,
                        fontWeight = FontWeight.Bold,
                    )
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Spacer(Modifier.size(100.dp))
                    Row() {

                        Image(
                            painterResource(id = R.drawable.mail),
                            contentDescription = "C'est le mail",
                            Modifier.size(30.dp)
                        )
                        Text(
                            text = "  alexia.bayol@etud.univ-jfc.fr",
                            fontSize = 25.sp,
                            color = Color.White
                        )
                        Spacer(Modifier.size(50.dp))
                    }
                    Row() {
                        Image(
                            painterResource(id = R.drawable.instagram),
                            contentDescription = "C'est l'insta",
                            Modifier.size(30.dp)
                        )
                        Text(
                            text = "  alexiabyl",
                            fontSize = 25.sp,
                            color = Color.White
                        )
                        Spacer(Modifier.size(70.dp))
                    }
                    Button(
                        onClick = { navController.navigate("Films") },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
                    ) {
                        Text(
                            text = "Démarrer",
                        )
                    }
                }
            }
        }
    }
}

