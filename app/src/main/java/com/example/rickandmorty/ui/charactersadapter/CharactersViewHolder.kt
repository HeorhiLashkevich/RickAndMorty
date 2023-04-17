package com.example.rickandmorty.ui.charactersadapter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmort.databinding.ItemCharactersBinding
import com.example.rickandmorty.api.CharactersResult
import com.squareup.picasso.Picasso

class CharactersViewHolder(
    private val binding: ItemCharactersBinding
) : RecyclerView.ViewHolder(binding.root) {


    @SuppressLint("SetTextI18n")
    fun bind(item: CharactersResult) {
        binding.run {
            characterName.text = item.name
            characterSpecies.text = item.species
            characterGender.text = item.gender
            characterStatus.text = item.status
            Picasso.get().load(item.image).into(characterImage)
        }
    }
}