package com.example.jetpackcomposeproofofconcept.domain

import com.icdominguez.network.api.MarvelApiRepository
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(
    private val marvelApiRepository: MarvelApiRepository
) {
    suspend operator fun invoke(apiKey: String, hash: String, offset: Int, ts: Long) = marvelApiRepository.getCharacters(apiKey, hash, offset, ts)
}