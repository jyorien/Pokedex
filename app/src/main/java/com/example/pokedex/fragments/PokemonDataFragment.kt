package com.example.pokedex.fragments

import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.updatePadding
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.pokedex.MainActivity
import com.example.pokedex.R
import com.example.pokedex.databinding.FragmentPokemonDataBinding
import com.example.pokedex.models.PokeData
import com.example.pokedex.services.PokeClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.math.ceil


class PokemonDataFragment : Fragment() {

    private lateinit var binding: FragmentPokemonDataBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as MainActivity).showUpButton()
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
            pokemonName.text = pokeName.replaceFirstChar { it.uppercase() }
            pokemonIndex.text = formattedIndex
        }
        response.types.forEach { pokeType ->
        val newBadge = TextView(requireContext())
            val metrics = DisplayMetrics()
            requireActivity().windowManager.getDefaultDisplay().getMetrics(metrics)
            val logicalDensity = metrics.density
            val dp_10 = ceil(10.0 * logicalDensity).toInt()
            val dp_3 = ceil(3.0 * logicalDensity).toInt()
            val params = LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            params.setMargins(dp_10, 0, dp_10, 0)
            newBadge.gravity = Gravity.CENTER_HORIZONTAL
            newBadge.updatePadding(dp_10, dp_3, dp_10, dp_3)
            newBadge.textSize = 14F
            newBadge.background = ContextCompat.getDrawable(requireContext(),R.drawable.type_badge_background)
            newBadge.layoutParams  = params
            Log.d("hello","wtf ${pokeType.type.name}")
            newBadge.text = pokeType.type.name.replaceFirstChar { it.uppercase() }
            binding.typeLayout.addView(newBadge)
        }
    }


}