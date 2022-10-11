package com.example.jetpackcomposeproofofconcept.domain

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.jetpackcomposeproofofconcept.domain.entity.CharacterEntity

@Database(entities = [CharacterEntity::class], version = 1)
abstract class MarvelDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
}
