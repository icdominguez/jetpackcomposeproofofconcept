package com.icdominguez.database.domain

import com.icdominguez.core.api.Character
import com.icdominguez.database.api.MarvelDatabaseRepository
import com.icdominguez.database.data.entity.CharacterEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetStoredCharacterUseCase @Inject constructor(
    private val marvelDatabaseRepository: MarvelDatabaseRepository
) {
    suspend operator fun invoke(characterId: Int): Flow<Character> = marvelDatabaseRepository.getCharacterById(characterId)
}
