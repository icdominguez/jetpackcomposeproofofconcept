package com.example.jetpackcomposeproofofconcept.domain.usecase.localdatabase

import com.example.jetpackcomposeproofofconcept.data.dao.CharacterDao
import javax.inject.Inject

class UpdateCharacterIsFavoriteValueUseCase @Inject constructor(
    private val characterDao: CharacterDao
) {
    suspend operator fun invoke(characterId: Int, isFavorite: Boolean) = characterDao.updateCharacterFavoriteValue(isFavorite, characterId)
}
