package com.example.rickandmorty.ui.episodesadapter

import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmort.databinding.ItemEpisodeBinding
import com.example.rickandmorty.api.EpisodesResult


class EpisodesViewHolder
    (
    private val binding: ItemEpisodeBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: EpisodesResult) {
        binding.run {
            episodeName.text = item.name
            episodeCreated.text = item.created
            episode.text = item.episode


        }
//        Picasso.get().load(item.url).into(binding.episodeImage)

    }
}