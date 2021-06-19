package com.example.pokedex.services

import com.example.pokedex.PokeResponse
import com.example.pokedex.Pokemon
import com.example.pokedex.models.PokeData
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeService {
    @GET("pokemon")
    suspend fun getPokemon(@Query("limit") limit: String): PokeResponse

    @GET("pokemon/{id}")
    suspend fun getPokemonData(@Path("id") index: String): PokeData
}