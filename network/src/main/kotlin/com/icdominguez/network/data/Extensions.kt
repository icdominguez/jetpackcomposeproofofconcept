package com.icdominguez.network.data

import com.icdominguez.core.api.Character
import com.icdominguez.network.data.model.responses.CharacterNetwork

object Extensions {
    fun CharacterNetwork.toCharacter(): Character = Character(
        characterId = this.id!!,
        name = this.name!!,
        description = this.description!!,
        image = "${this.thumbnail!!.path}.${this.thumbnail!!.extension}",
        favorite = false
    )
}
