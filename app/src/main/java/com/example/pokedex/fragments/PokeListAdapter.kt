package com.example.pokedex.fragments

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.R

class PokeListAdapter(private val pokemonList: ArrayList<String>): RecyclerView.Adapter<PokeListAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView = itemView.findViewById<TextView>(R.id.textView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokeListAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.poke_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: PokeListAdapter.ViewHolder, position: Int) {
        holder.textView.text = pokemonList[position]
    }

    override fun getItemCount(): Int = pokemonList.size
}