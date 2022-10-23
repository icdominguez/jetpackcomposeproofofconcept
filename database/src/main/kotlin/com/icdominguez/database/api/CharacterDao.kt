package com.icdominguez.database.api

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.icdominguez.database.data.entity.CharacterEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {
    @Query("SELECT * FROM character")
    fun getAll(): Flow<List<CharacterEntity>>

    @Query("UPDATE character SET is_favorite = :favorite WHERE id = :characterId")
    suspend fun updateCharacterFavoriteValue(favorite: Boolean, characterId: Int)

    @Query("SELECT * FROM character WHERE id = :characterId")
    fun getCharacterById(characterId: Int): Flow<CharacterEntity>

    @Insert
    suspend fun insertAll(characterList: List<CharacterEntity>)
}
