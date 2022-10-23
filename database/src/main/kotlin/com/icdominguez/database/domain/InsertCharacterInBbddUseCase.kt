package com.icdominguez.database.domain

import com.icdominguez.database.api.MarvelDatabaseRepository
import com.icdominguez.database.data.entity.CharacterEntity
import javax.inject.Inject

class InsertCharacterInBbddUseCase @Inject constructor(
    private val marvelDatabaseRepository: MarvelDatabaseRepository
) {
    suspend operator fun invoke(characterList: List<CharacterEntity>) = marvelDatabaseRepository.insertCharacters(characterList = characterList)
}
