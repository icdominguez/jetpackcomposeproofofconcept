package com.icdominguez.database.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.icdominguez.database.api.CharacterDao
import com.icdominguez.database.data.entity.CharacterEntity

@Database(entities = [CharacterEntity::class], version = 1)
abstract class MarvelDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
}
