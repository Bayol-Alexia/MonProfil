package com.example.monprofil

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels

import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi

import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.example.monprofil.ui.theme.MonProfilTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel :  MainViewModel by viewModels()
        setContent {
            MonProfilTheme {
                // A surface container using the 'background' color from the theme
                val windowSizeClass = calculateWindowSizeClass(this)
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "Profil") {
                    composable("Profil") { Profil(navController, windowSizeClass) }
                    composable("Films") { FilmsScreen(navController, windowSizeClass, viewModel) }
                }
             }
            }
        }
}





