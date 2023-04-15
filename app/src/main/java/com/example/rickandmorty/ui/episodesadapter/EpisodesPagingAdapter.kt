package com.example.rickandmorty.ui.episodesadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.example.rickandmort.databinding.ItemEpisodeBinding
import com.example.rickandmorty.api.EpisodesResult

class EpisodesPagingAdapter : PagingDataAdapter<EpisodesResult, EpisodesViewHolder>(
    EpisodesDiffUtil()
) {
    override fun onBindViewHolder(holder: EpisodesViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodesViewHolder {
        return EpisodesViewHolder(
            ItemEpisodeBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

}


