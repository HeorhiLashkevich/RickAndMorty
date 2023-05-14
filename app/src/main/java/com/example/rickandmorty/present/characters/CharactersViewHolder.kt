package com.example.rickandmorty.present.characters

import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmort.databinding.ItemCharactersBinding
import com.example.rickandmorty.data.model.CharactersEntity
import com.squareup.picasso.Picasso


class CharactersViewHolder(
    private val binding: ItemCharactersBinding

) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: CharactersEntity) {
        binding.run {
            characterName.text = item.name
            characterSpecies.text = item.species
            characterGender.text = item.gender
            characterStatus.text = item.status
            Picasso.get().load(item.image).into(characterImage)

        }
    }


}