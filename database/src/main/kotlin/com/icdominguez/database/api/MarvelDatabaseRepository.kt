package com.icdominguez.database.api

import com.icdominguez.core.api.Character
import kotlinx.coroutines.flow.Flow

interface MarvelDatabaseRepository {
    suspend fun insertCharacters(characterList: List<Character>)
    suspend fun getCharacterById(characterId: Int): Flow<Character>
    suspend fun updateCharacterIsFavorite(characterId: Int, isFavorite: Boolean)
    fun getAllCharacters(): Flow<List<Character>>
}
