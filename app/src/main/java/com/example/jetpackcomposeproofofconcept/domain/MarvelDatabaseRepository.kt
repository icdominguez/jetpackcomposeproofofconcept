package com.example.jetpackcomposeproofofconcept.domain

import com.example.jetpackcomposeproofofconcept.domain.entity.CharacterEntity
import kotlinx.coroutines.flow.Flow

interface MarvelDatabaseRepository {
    suspend fun insertCharacters(characterList: List<CharacterEntity>)
    fun getAllCharacters(): Flow<List<CharacterEntity>>
}