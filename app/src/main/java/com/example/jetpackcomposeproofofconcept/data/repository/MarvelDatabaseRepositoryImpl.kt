package com.example.jetpackcomposeproofofconcept.data.repository

import com.example.jetpackcomposeproofofconcept.data.dao.CharacterDao
import com.example.jetpackcomposeproofofconcept.data.model.entities.CharacterEntity
import com.example.jetpackcomposeproofofconcept.domain.repository.MarvelDatabaseRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MarvelDatabaseRepositoryImpl @Inject constructor(
    private val characterDao: CharacterDao
) : MarvelDatabaseRepository {
    override suspend fun insertCharacters(characterList: List<CharacterEntity>) = characterDao.insertAll(characterList)
    override fun getAllCharacters(): Flow<List<CharacterEntity>> = characterDao.getAll()
}
