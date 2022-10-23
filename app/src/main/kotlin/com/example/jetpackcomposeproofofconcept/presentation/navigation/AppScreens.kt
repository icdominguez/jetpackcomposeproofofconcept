package com.example.jetpackcomposeproofofconcept.presentation.navigation

sealed class AppScreens(val route: String) {
    object SplashScreen : AppScreens("splash_screen")
    object MainScreen : AppScreens("main_screen")
    object CharacterScreen : AppScreens("character_screen")
}
