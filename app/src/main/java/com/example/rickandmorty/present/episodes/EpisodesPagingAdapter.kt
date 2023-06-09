package com.example.rickandmorty.present.episodes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.example.rickandmort.databinding.ItemEpisodesBinding
import com.example.rickandmorty.data.local.paging.datasource.EpisodesDiffUtil
import com.example.rickandmorty.data.model.EpisodeEntity

class EpisodesPagingAdapter(
    private val onClick: (id: Int) -> Unit
) : PagingDataAdapter<EpisodeEntity, EpisodesViewHolder>(EpisodesDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodesViewHolder {
        return EpisodesViewHolder(
            ItemEpisodesBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: EpisodesViewHolder, position: Int) {
        getItem(position)?.let { id ->
            holder.bind(id)
            holder.itemView.setOnClickListener {
                onClick(id.id.toInt())
            }
        }

    }


}


