package com.icdominguez.network.api

import com.icdominguez.core.api.Character
import com.icdominguez.network.data.model.responses.CharactersResponse
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
    ): Flow<BaseResult<Character, WrappedResponse<CharactersResponse>>>
}
