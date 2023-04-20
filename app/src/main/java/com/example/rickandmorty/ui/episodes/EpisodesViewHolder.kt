package com.example.rickandmorty.ui.episodes

import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmort.databinding.ItemEpisodesBinding
import com.example.rickandmorty.api.EpisodesResult


class EpisodesViewHolder
    (
    private val binding: ItemEpisodesBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: EpisodesResult) {
        binding.run {
            episodeName.text = item.name
            episodeNumber.text = item.episode
            episodeDateRelease.text = item.air_date
        }

    }
}