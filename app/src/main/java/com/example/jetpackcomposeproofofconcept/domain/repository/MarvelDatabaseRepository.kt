package com.example.jetpackcomposeproofofconcept.domain.repository

import com.example.jetpackcomposeproofofconcept.data.model.entities.CharacterEntity
import kotlinx.coroutines.flow.Flow

interface MarvelDatabaseRepository {
    suspend fun insertCharacters(characterList: List<CharacterEntity>)
    fun getAllCharacters(): Flow<List<CharacterEntity>>
}
