package com.example.jetpackcomposeproofofconcept.domain.usecase.api

import com.example.jetpackcomposeproofofconcept.domain.repository.MarvelApiRepository
import javax.inject.Inject

class GetCharacterUseCase @Inject constructor(
    private val marvelApiRepository: MarvelApiRepository
) {
    suspend operator fun invoke(
        id: Int,
        apiKey: String,
        hash: String,
        ts: Long
    ) = marvelApiRepository.getCharacter(id, apiKey, hash, ts)
}
