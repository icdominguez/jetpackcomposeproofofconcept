package com.icdominguez.network.domain

import com.icdominguez.network.api.MarvelApiRepository
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
