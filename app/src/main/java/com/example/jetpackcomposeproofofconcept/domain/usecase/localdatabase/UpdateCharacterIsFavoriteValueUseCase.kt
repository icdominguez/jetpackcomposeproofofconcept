package com.example.jetpackcomposeproofofconcept.domain.usecase.localdatabase

import com.example.jetpackcomposeproofofconcept.domain.CharacterDao
import javax.inject.Inject

class UpdateCharacterIsFavoriteValueUseCase @Inject constructor(
    private val characterDao: CharacterDao
) {
    private operator fun invoke(isFavorite: Boolean, characterId: Int) = characterDao.updateCharacterFavoriteValue(isFavorite, characterId)
}