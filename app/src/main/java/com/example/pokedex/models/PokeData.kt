package com.example.pokedex.models

import com.squareup.moshi.Json

data class PokeData(
    val sprites: SpriteObject,
    val types: List<PokeType>

    )

data class PokeType(
    val slot: Int,
    val type: TypeName
)

data class TypeName(
    val name: String,
    val url: String
)

data class SpriteObject(
    @field:Json(name = "front_default") val spriteUrl: String
)

/*
{
"types": [
{
"slot": 1,
"type": {
"name": "normal",
"url": "https://pokeapi.co/api/v2/type/1/"
}
}
}
 */