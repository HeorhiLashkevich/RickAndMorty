package com.example.rickandmorty.ui.charactersadapter

import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmort.databinding.ItemCharacterBinding
import com.example.rickandmorty.api.CharactersResult
import com.squareup.picasso.Picasso

class CharactersViewHolder(
    private val binding: ItemCharacterBinding
) : RecyclerView.ViewHolder(binding.root) {


    fun bind(item: CharactersResult) {
        binding.run {
            characterName.text = item.name
            characterGender.text = item.gender
            characterLocation.text = item.location.name
            Picasso.get().load(item.image).into(binding.characterImage)
        }
    }
}