package com.example.pokedex.services

import com.squareup.moshi.Moshi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object PokeClient {
    private const val baseUrl = "https://pokeapi.co/api/v2/"
    val moshi = Moshi.Builder()
        .build()
    val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()
    val api: PokeService by lazy {
        retrofit.create(PokeService::class.java)
    }


}