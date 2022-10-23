package com.icdominguez.database.presentation

import com.icdominguez.database.api.MarvelDatabaseRepository
import com.icdominguez.database.api.CharacterDao
import com.icdominguez.database.data.entity.CharacterEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MarvelDatabaseRepositoryImpl @Inject constructor(
    private val characterDao: CharacterDao
) : MarvelDatabaseRepository {
    override suspend fun insertCharacters(characterList: List<CharacterEntity>) = characterDao.insertAll(characterList)
    override suspend fun getCharacterById(characterId: Int): Flow<CharacterEntity> = characterDao.getCharacterById(characterId)
    override fun getAllCharacters(): Flow<List<CharacterEntity>> = characterDao.getAll()
}
