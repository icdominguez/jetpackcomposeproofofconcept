package com.example.jetpackcomposeproofofconcept.data

import com.example.jetpackcomposeproofofconcept.domain.CharacterDao
import com.example.jetpackcomposeproofofconcept.domain.MarvelDatabaseRepository
import com.example.jetpackcomposeproofofconcept.domain.entity.CharacterEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MarvelDatabaseRepositoryImpl @Inject constructor(
    private val characterDao: CharacterDao
) : MarvelDatabaseRepository {
    override suspend fun insertCharacters(characterList: List<CharacterEntity>) = characterDao.insertAll(characterList)
    override fun getAllCharacters(): Flow<List<CharacterEntity>> = characterDao.getAll()
}
