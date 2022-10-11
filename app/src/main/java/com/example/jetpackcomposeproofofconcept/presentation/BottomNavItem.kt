package com.example.jetpackcomposeproofofconcept.presentation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(var route: String, var icon: ImageVector, var title: String) {
    object Character : BottomNavItem("characters", Icons.Filled.Home, "Characters")
    /*object Comics : BottomNavItem("comics", R.drawable.ic_comic, "Comics")
    object Favorites : BottomNavItem("favorites", R.drawable.ic_favorite, "Favorites")*/
}
