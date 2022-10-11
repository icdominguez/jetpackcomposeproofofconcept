package com.example.jetpackcomposeproofofconcept.domain

import com.example.jetpackcomposeproofofconcept.data.WrappedResponse
import com.example.jetpackcomposeproofofconcept.data.model.Character
import com.example.jetpackcomposeproofofconcept.data.model.CharactersResponse
import com.example.jetpackcomposeproofofconcept.domain.entity.CharacterEntity
import kotlinx.coroutines.flow.Flow

interface MarvelApiRepository {
    suspend fun getCharacters(
        apiKey: String,
        hash: String,
        offset: Int,
        ts: Long
    ): Flow<BaseResult<List<Character>, WrappedResponse<CharactersResponse>>>

    suspend fun getCharacter(
        id: Int,
        apiKey: String,
        hash: String,
        ts: Long
    ): Flow<BaseResult<CharacterEntity, WrappedResponse<CharactersResponse>>>
}