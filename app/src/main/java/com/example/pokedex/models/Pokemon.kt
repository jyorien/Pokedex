package com.example.pokedex

data class PokeResponse(
    val results: List<Pokemon>,
)
data class Pokemon(
    val name: String,
    val url: String
)