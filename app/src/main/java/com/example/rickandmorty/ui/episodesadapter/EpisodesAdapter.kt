package com.example.rickandmorty.ui.episodesadapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmort.databinding.ItemEpisodeBinding
import com.example.rickandmorty.api.EpisodesResult

class EpisodesAdapter() : RecyclerView.Adapter<EpisodesViewHolder>() {
    private var list = listOf<EpisodesResult>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodesViewHolder {
        return EpisodesViewHolder(
            ItemEpisodeBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount() = list.size


    override fun onBindViewHolder(holder: EpisodesViewHolder, position: Int) {
        holder.bind(list[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: ArrayList<EpisodesResult>) {
        this.list = list
        notifyDataSetChanged()

    }

}