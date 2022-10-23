package com.icdominguez.database.domain

import com.icdominguez.database.api.MarvelDatabaseRepository
import javax.inject.Inject

class GetStoredCharactersUseCase @Inject constructor(
    private val marvelDatabaseRepository: MarvelDatabaseRepository
) {
    operator fun invoke() = marvelDatabaseRepository.getAllCharacters()
}
