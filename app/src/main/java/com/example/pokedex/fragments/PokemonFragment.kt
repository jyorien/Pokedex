package com.example.pokedex.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pokedex.PokeResponse
import com.example.pokedex.Pokemon
import com.example.pokedex.R
import com.example.pokedex.services.PokeClient
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class PokemonFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadPokemon()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pokemon, container, false)
    }

    private fun loadPokemon() {
        GlobalScope.launch {
            try {
                val response = PokeClient.api.getPokemon("10")
                Log.d("hello", response.toString())

            } catch (e: Exception) {
                Log.d("hello",e.toString())
            }
        }

    }

}