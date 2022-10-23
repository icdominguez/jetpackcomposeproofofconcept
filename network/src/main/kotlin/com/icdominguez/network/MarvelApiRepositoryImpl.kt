package com.icdominguez.network

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.icdominguez.network.api.MarvelApiRepository
import com.icdominguez.network.data.model.BaseResult
import com.icdominguez.network.data.model.WrappedResponse
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
    ): Flow<BaseResult<CharactersResponse, WrappedResponse<CharactersResponse>>> {
        return flow {
            val response = marvelApiService.getCharacters(apiKey = apiKey, hash = hash, offset = offset, ts = ts)

            if (response.isSuccessful) {
                val body = response.body()!!
                emit(BaseResult.Success(body))
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
    ): Flow<BaseResult<CharactersResponse, WrappedResponse<CharactersResponse>>> = flow {
        val response = marvelApiService.getCharacter(id = id, apikey = apiKey, hash = hash, ts = ts)

        if (response.isSuccessful) {
            val body = response.body()!!
            emit(
                BaseResult.Success(body)
            )
        } else {
            val type = object : TypeToken<WrappedResponse<CharactersResponse>>() {}.type
            val error: WrappedResponse<CharactersResponse> = Gson().fromJson(response.errorBody()!!.charStream(), type)
            error.code = response.code()
            emit(BaseResult.Failure(error))
        }
    }
}
