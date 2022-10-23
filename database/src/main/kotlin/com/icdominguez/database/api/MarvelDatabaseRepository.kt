package com.icdominguez.database.api

import com.icdominguez.database.data.entity.CharacterEntity
import kotlinx.coroutines.flow.Flow

interface MarvelDatabaseRepository {
    suspend fun insertCharacters(characterList: List<CharacterEntity>)
    suspend fun getCharacterById(characterId: Int): Flow<CharacterEntity>
    fun getAllCharacters(): Flow<List<CharacterEntity>>
}
