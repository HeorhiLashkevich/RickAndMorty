package com.example.rickandmorty.present.episodes

import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmort.databinding.ItemEpisodesBinding
import com.example.rickandmorty.data.model.EpisodeEntity


class EpisodesViewHolder
    (
    private val binding: ItemEpisodesBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: EpisodeEntity) {
        binding.run {
            episodeName.text = item.name
            episodeNumber.text = item.episode
            episodeDateRelease.text = item.air_date
        }

    }
}