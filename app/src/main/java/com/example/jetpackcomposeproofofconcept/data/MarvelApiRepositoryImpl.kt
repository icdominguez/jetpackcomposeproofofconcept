package com.example.jetpackcomposeproofofconcept.data

import com.example.jetpackcomposeproofofconcept.data.model.Character
import com.example.jetpackcomposeproofofconcept.data.model.CharactersResponse
import com.example.jetpackcomposeproofofconcept.domain.BaseResult
import com.example.jetpackcomposeproofofconcept.domain.MarvelApiRepository
import com.example.jetpackcomposeproofofconcept.domain.entity.CharacterEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MarvelApiRepositoryImpl @Inject constructor(
    private val marvelApiService: MarvelApiService
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
                val characterList = body.data?.results
                emit(BaseResult.Success(characterList!!))
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
        ts: Long,
    ): Flow<BaseResult<CharacterEntity, WrappedResponse<CharactersResponse>>> = flow {
        val response = marvelApiService.getCharacter(id = id, apikey = apiKey, hash = hash, ts = ts)

        if (response.isSuccessful) {
            val body = response.body()!!
            val character = body.data?.results?.get(0)
            emit(
                BaseResult.Success(
                    CharacterEntity(
                        id = character?.id!!,
                        name = character.name!!,
                        description = character.description!!,
                        image = "${character.thumbnail!!.path}.${character.thumbnail!!.extension}",
                        isFavorite = false
                    )
                )
            )
        } else {
            val type = object : TypeToken<WrappedResponse<CharactersResponse>>() {}.type
            val error: WrappedResponse<CharactersResponse> = Gson().fromJson(response.errorBody()!!.charStream(), type)
            error.code = response.code()
            emit(BaseResult.Failure(error))
        }
    }
}
