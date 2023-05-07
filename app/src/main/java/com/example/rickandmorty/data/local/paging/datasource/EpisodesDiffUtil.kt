package com.example.rickandmorty.data.local.paging.datasource

import androidx.recyclerview.widget.DiffUtil
import com.example.rickandmorty.data.model.EpisodeEntity

class EpisodesDiffUtil : DiffUtil.ItemCallback<EpisodeEntity>() {
    override fun areItemsTheSame(oldItem: EpisodeEntity, newItem: EpisodeEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: EpisodeEntity, newItem: EpisodeEntity): Boolean {
        return oldItem == newItem
    }
}