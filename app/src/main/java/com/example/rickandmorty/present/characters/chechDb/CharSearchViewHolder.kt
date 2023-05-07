package com.example.rickandmorty.present.characters.chechDb

import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmort.databinding.ItemCharactersBinding
import com.example.rickandmorty.api.CharactersResult
import com.squareup.picasso.Picasso

class CharSearchViewHolder(
    private val binding: ItemCharactersBinding
) : RecyclerView.ViewHolder(binding.root) {

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