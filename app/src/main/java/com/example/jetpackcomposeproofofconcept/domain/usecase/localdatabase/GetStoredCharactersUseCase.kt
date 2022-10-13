package com.example.jetpackcomposeproofofconcept.domain.usecase.localdatabase

import com.example.jetpackcomposeproofofconcept.domain.repository.MarvelDatabaseRepository
import javax.inject.Inject

class GetStoredCharactersUseCase @Inject constructor(
    private val marvelDatabaseRepository: MarvelDatabaseRepository
) {
    operator fun invoke() = marvelDatabaseRepository.getAllCharacters()
}
