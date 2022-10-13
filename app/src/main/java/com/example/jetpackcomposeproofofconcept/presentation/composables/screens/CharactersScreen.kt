package com.example.jetpackcomposeproofofconcept.presentation.composables.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.jetpackcomposeproofofconcept.presentation.composables.CharacterItem
import com.example.jetpackcomposeproofofconcept.presentation.navigation.AppScreens
import com.example.jetpackcomposeproofofconcept.presentation.viewmodels.CharactersViewModel

@Composable
fun CharactersScreen(navController: NavController) {
    val viewModel = hiltViewModel<CharactersViewModel>()

    val state by viewModel.mState.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = CenterHorizontally
    ) {
        LazyVerticalGrid(columns = GridCells.Adaptive(minSize = 128.dp)) {
            state.listCharacter.forEach { character ->
                item {
                    Column(
                        modifier = Modifier.fillMaxSize().clickable {
                            navController.navigate(route = AppScreens.CharacterScreen.route + "/${character.id}")
                        },
                        horizontalAlignment = CenterHorizontally
                    ) {
                        CharacterItem(
                            character.id,
                            character.name,
                            character.image,
                            character.isFavorite
                        )
                    }
                }
            }
        }
    }
}

@Composable
@Preview
fun CharactersScreenPreview() {
}
