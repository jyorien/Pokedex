package com.example.pokedex.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.pokedex.R
import com.example.pokedex.databinding.FragmentPokemonBinding
import com.example.pokedex.databinding.FragmentPokemonDataBinding
import com.example.pokedex.models.PokeData
import com.example.pokedex.services.PokeClient
import com.example.pokedex.services.PokeService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class PokemonDataFragment : Fragment() {

    private lateinit var binding: FragmentPokemonDataBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_pokemon_data, container, false)
        getPokeData()
        return binding.root
    }

    private fun getPokeData() {
        val index = requireArguments()["index"] as String
        val pokeName = requireArguments()["pokeName"] as String
        GlobalScope.launch {
            try {
                val response = PokeClient.api.getPokemonData(index)
                withContext(Dispatchers.Main) {
                    setPokeData(response, pokeName, index)
                }


                Log.d("hello",response.toString())
            } catch (e: Exception) {
                Log.d("hello",e.toString())
            }

        }
    }

    private fun setPokeData(response: PokeData, pokeName: String, index: String) {
        Glide.with(requireContext())
            .load(response.sprites.spriteUrl)
            .into(binding.pokeSprite)
        val formattedIndex = "#${index.padStart(3, '0')}"
        binding.apply {
            pokemonName.text = pokeName
            pokemonIndex.text = formattedIndex
        }
    }


}