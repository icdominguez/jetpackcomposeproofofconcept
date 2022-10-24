package com.icdominguez.database.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.icdominguez.core.api.Character

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

fun CharacterEntity.toCharacter() = Character(
    characterId = this.id,
    name = this.name,
    description = this.description,
    image = this.image,
    favorite = this.isFavorite
)
