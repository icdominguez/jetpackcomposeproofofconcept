package com.example.jetpackcomposeproofofconcept.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.jetpackcomposeproofofconcept.data.model.entities.CharacterEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {
    @Query("SELECT * FROM character")
    fun getAll(): Flow<List<CharacterEntity>>

    @Query("UPDATE character SET is_favorite = :favorite WHERE id = :characterId")
    fun updateCharacterFavoriteValue(favorite: Boolean, characterId: Int)

    @Insert
    suspend fun insertAll(characterList: List<CharacterEntity>)
}
