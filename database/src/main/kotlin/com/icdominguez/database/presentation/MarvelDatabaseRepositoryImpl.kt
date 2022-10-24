package com.icdominguez.database.presentation

import com.icdominguez.core.api.Character
import com.icdominguez.database.api.CharacterDao
import com.icdominguez.database.api.MarvelDatabaseRepository
import com.icdominguez.database.data.entity.CharacterEntity
import com.icdominguez.database.data.entity.toCharacter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MarvelDatabaseRepositoryImpl @Inject constructor(
    private val characterDao: CharacterDao
) : MarvelDatabaseRepository {
    override suspend fun insertCharacters(characterList: List<Character>) {
        val characterEntityList = arrayListOf<CharacterEntity>()

        characterList.map { character ->
            characterEntityList.add(
                CharacterEntity(character.characterId, character.name, character.description, character.image, character.favorite)
            )
        }

        characterDao.insertAll(characterEntityList)
    }
    override suspend fun getCharacterById(characterId: Int): Flow<Character> {
        return flow {
            characterDao.getCharacterById(characterId).collect {
                it.toCharacter()
            }
        }
    }

    override suspend fun updateCharacterIsFavorite(characterId: Int, isFavorite: Boolean) = characterDao.updateCharacterFavoriteValue(isFavorite, characterId)

    override fun getAllCharacters(): Flow<List<Character>> {
        return flow {
            val characterList = arrayListOf<Character>()
            characterDao.getAll().collect { characterEntityList ->
                characterEntityList.map {
                    characterList.add(it.toCharacter())
                }
            }
        }
    }
}
