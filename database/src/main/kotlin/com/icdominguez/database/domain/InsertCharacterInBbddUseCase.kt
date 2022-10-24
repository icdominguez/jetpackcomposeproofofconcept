package com.icdominguez.database.domain

import com.icdominguez.core.api.Character
import com.icdominguez.database.api.MarvelDatabaseRepository
import javax.inject.Inject

class InsertCharacterInBbddUseCase @Inject constructor(
    private val marvelDatabaseRepository: MarvelDatabaseRepository
) {
    suspend operator fun invoke(characterList: List<Character>) = marvelDatabaseRepository.insertCharacters(characterList = characterList)
}
