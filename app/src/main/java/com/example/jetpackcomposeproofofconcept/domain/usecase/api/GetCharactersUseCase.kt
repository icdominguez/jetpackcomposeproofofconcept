package com.example.jetpackcomposeproofofconcept.domain.usecase.api

import com.example.jetpackcomposeproofofconcept.data.model.BaseResult
import com.example.jetpackcomposeproofofconcept.data.model.Character
import com.example.jetpackcomposeproofofconcept.data.model.CharactersResponse
import com.example.jetpackcomposeproofofconcept.data.model.WrappedResponse
import com.example.jetpackcomposeproofofconcept.domain.repository.MarvelApiRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(
    private val marvelApiRepository: MarvelApiRepository
) {
    suspend operator fun invoke(apiKey: String, hash: String, offset: Int, ts: Long): Flow<BaseResult<List<Character>, WrappedResponse<CharactersResponse>>> =
        marvelApiRepository.getCharacters(apiKey, hash, offset, ts)
}
