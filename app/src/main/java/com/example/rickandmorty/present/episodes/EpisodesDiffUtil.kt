package com.example.rickandmorty.present.episodes

import androidx.recyclerview.widget.DiffUtil
import com.example.rickandmorty.api.EpisodesResult

class EpisodesDiffUtil : DiffUtil.ItemCallback<EpisodesResult>() {
    override fun areItemsTheSame(oldItem: EpisodesResult, newItem: EpisodesResult): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: EpisodesResult, newItem: EpisodesResult): Boolean {
        return oldItem == newItem
    }
}