package com.example.jetpackcomposeproofofconcept.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.jetpackcomposeproofofconcept.data.dao.CharacterDao
import com.example.jetpackcomposeproofofconcept.data.model.entities.CharacterEntity

@Database(entities = [CharacterEntity::class], version = 1)
abstract class MarvelDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
}
