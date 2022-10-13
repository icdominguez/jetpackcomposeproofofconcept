package com.example.jetpackcomposeproofofconcept.domain.usecase.localdatabase

import com.example.jetpackcomposeproofofconcept.data.dao.CharacterDao
import com.example.jetpackcomposeproofofconcept.data.model.entities.CharacterEntity
import javax.inject.Inject

class GetStoredCharacterUseCase @Inject constructor(
    private val characterDao: CharacterDao
) {
    suspend operator fun invoke(characterId: Int): CharacterEntity = characterDao.getCharacterById(characterId)
}
