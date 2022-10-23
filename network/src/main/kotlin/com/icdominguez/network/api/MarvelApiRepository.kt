package com.icdominguez.network.api

import com.example.jetpackcomposeproofofconcept.data.model.BaseResult
import com.icdominguez.network.data.model.responses.Character
import com.icdominguez.network.data.model.responses.CharactersResponse
import com.example.jetpackcomposeproofofconcept.data.model.WrappedResponse
import com.icdominguez.database.data.entity.CharacterEntity
import com.icdominguez.network.data.model.BaseResult
import com.icdominguez.network.data.model.WrappedResponse
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
