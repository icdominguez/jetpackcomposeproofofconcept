package com.icdominguez.database.domain

import com.icdominguez.database.api.CharacterDao
import javax.inject.Inject

class UpdateCharacterIsFavoriteValueUseCase @Inject constructor(
    private val characterDao: CharacterDao
) {
    suspend operator fun invoke(characterId: Int, isFavorite: Boolean) = characterDao.updateCharacterFavoriteValue(isFavorite, characterId)
}
