package com.example.rickandmorty.ui.episodesdetailsadapter

import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmort.databinding.ItemCharactersBinding
import com.example.rickandmorty.api.CharactersResult
import com.squareup.picasso.Picasso

class EpisodeDetailsViewHolder     (
    private val binding: ItemCharactersBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: CharactersResult) {
        binding.run {
            characterName.text = item.name
            characterSpecies.text = item.species
            characterStatus.text = item.status
            characterGender.text = item.gender
            Picasso.get().load(item.image).into(characterImage)

        }

    }
}