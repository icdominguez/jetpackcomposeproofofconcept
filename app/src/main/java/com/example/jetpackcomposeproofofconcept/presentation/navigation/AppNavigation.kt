package com.example.jetpackcomposeproofofconcept.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.jetpackcomposeproofofconcept.presentation.composables.screens.CharacterScreen
import com.example.jetpackcomposeproofofconcept.presentation.composables.screens.CharactersScreen
import com.example.jetpackcomposeproofofconcept.presentation.composables.screens.SplashScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.SplashScreen.route) {
        composable(AppScreens.SplashScreen.route) {
            SplashScreen(navController)
        }
        composable(AppScreens.MainScreen.route) {
            CharactersScreen(navController)
        }
        composable(
            route = AppScreens.CharacterScreen.route + "/{id}",
            arguments = listOf(
                navArgument(name = "id") {
                    type = NavType.IntType
                }
            )
        ) {
            CharacterScreen()
        }
    }
}
