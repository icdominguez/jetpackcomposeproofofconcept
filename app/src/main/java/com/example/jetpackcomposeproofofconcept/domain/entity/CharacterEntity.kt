package com.example.jetpackcomposeproofofconcept.domain.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "character")
data class CharacterEntity(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "description")
    var description: String,
    @ColumnInfo(name = "image")
    var image: String,
    @ColumnInfo(name = "is_favorite")
    var isFavorite: Boolean
)