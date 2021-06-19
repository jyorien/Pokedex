package com.example.pokedex.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import com.example.pokedex.PokeResponse
import com.example.pokedex.Pokemon
import com.example.pokedex.R
import com.example.pokedex.databinding.FragmentPokemonBinding
import com.example.pokedex.services.PokeClient
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class PokemonFragment : Fragment() {
    private lateinit var binding: FragmentPokemonBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_pokemon, container, false)
        loadPokemon()
        return binding.root
    }

    private fun loadPokemon() {
        GlobalScope.launch {
            try {
                val response = PokeClient.api.getPokemon("20")
                val pokeList = arrayListOf<String>()
                Log.d("hello", response.toString())
                response.results.forEach { pokemon ->
                    pokeList.add(pokemon.name)
                }
                withContext(Dispatchers.Main) {
                    binding.pokeList.adapter = PokeListAdapter(pokeList)

                }

            } catch (e: Exception) {
                Log.d("hello",e.toString())
            }
        }

    }

}