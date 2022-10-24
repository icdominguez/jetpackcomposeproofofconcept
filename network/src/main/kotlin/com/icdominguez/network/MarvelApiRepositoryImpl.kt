package com.icdominguez.network

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.icdominguez.core.api.Character
import com.icdominguez.network.api.MarvelApiRepository
import com.icdominguez.network.data.Extensions.toCharacter
import com.icdominguez.network.api.BaseResult
import com.icdominguez.network.api.WrappedResponse
import com.icdominguez.network.data.model.responses.CharactersResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MarvelApiRepositoryImpl @Inject constructor(
    private val marvelApiService: com.icdominguez.network.api.MarvelApiService
) : MarvelApiRepository {

    override suspend fun getCharacters(
        apiKey: String,
        hash: String,
        offset: Int,
        ts: Long
    ): Flow<BaseResult<List<Character>, WrappedResponse<CharactersResponse>>> {
        return flow {
            val response = marvelApiService.getCharacters(apiKey = apiKey, hash = hash, offset = offset, ts = ts)

            if (response.isSuccessful) {
                val body = response.body()!!

                val characterList = arrayListOf<Character>()

                body.data?.results?.let { characterNetworkList ->
                    characterNetworkList.map { characterNetwork ->
                        characterList.add(characterNetwork.toCharacter())
                    }
                }

                emit(BaseResult.Success(characterList))
            } else {
                val type = object : TypeToken<WrappedResponse<CharactersResponse>>() {}.type
                val error: WrappedResponse<CharactersResponse> = Gson().fromJson(response.errorBody()!!.charStream(), type)
                error.code = response.code()
                emit(BaseResult.Failure(error))
            }
        }
    }

    override suspend fun getCharacter(
        id: Int,
        apiKey: String,
        hash: String,
        ts: Long
    ): Flow<BaseResult<Character, WrappedResponse<CharactersResponse>>> = flow {
        val response = marvelApiService.getCharacter(id = id, apikey = apiKey, hash = hash, ts = ts)

        if (response.isSuccessful) {
            val body = response.body()!!
            val characterNetwork = body.data!!.results!![0]
            emit(
                BaseResult.Success(characterNetwork.toCharacter())
            )
        } else {
            val type = object : TypeToken<WrappedResponse<CharactersResponse>>() {}.type
            val error: WrappedResponse<CharactersResponse> = Gson().fromJson(response.errorBody()!!.charStream(), type)
            error.code = response.code()
            emit(BaseResult.Failure(error))
        }
    }
}
