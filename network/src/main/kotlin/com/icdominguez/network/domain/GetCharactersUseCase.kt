package com.icdominguez.network.domain

import com.icdominguez.core.api.Character
import com.icdominguez.network.api.MarvelApiRepository
import com.icdominguez.network.api.BaseResult
import com.icdominguez.network.api.WrappedResponse
import com.icdominguez.network.data.model.responses.CharactersResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(
    private val marvelApiRepository: MarvelApiRepository
) {
    suspend operator fun invoke(apiKey: String, hash: String, offset: Int, ts: Long): Flow<BaseResult<List<Character>, WrappedResponse<CharactersResponse>>> =
        marvelApiRepository.getCharacters(apiKey, hash, offset, ts)
}
