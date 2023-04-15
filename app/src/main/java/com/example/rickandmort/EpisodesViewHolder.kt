package com.example.rickandmort

import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmort.api.EpisodeResult
import com.example.rickandmort.databinding.ItemEpisodeBinding


class EpisodesViewHolder
    (
    private val binding: ItemEpisodeBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun binding(item: EpisodeResult) {
        binding.run {
            episodeName.text = item.name
            episodeCreated.text = item.created
            episode.text = item.episode


        }
//        Picasso.get().load(item.url).into(binding.episodeImage)

    }
}