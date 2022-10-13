package com.example.jetpackcomposeproofofconcept.domain.usecase.localdatabase

import com.example.jetpackcomposeproofofconcept.data.model.entities.CharacterEntity
import com.example.jetpackcomposeproofofconcept.domain.repository.MarvelDatabaseRepository
import javax.inject.Inject

class InsertCharacterInBbddUseCase @Inject constructor(
    private val marvelDatabaseRepository: MarvelDatabaseRepository
) {
    suspend operator fun invoke(characterList: List<CharacterEntity>) = marvelDatabaseRepository.insertCharacters(characterList = characterList)
}
