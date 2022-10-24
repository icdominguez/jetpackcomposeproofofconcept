package com.icdominguez.core.api

data class Character(
    val characterId: Int,
    val name: String,
    val description: String,
    val image: String,
    val favorite: Boolean
)
