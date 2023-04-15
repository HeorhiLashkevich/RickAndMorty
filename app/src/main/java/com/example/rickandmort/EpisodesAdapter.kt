package com.example.rickandmort

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmort.api.EpisodeResult
import com.example.rickandmort.databinding.ItemEpisodeBinding

class EpisodesAdapter() : RecyclerView.Adapter<EpisodesViewHolder>() {
    private var list = listOf<EpisodeResult>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodesViewHolder {
        return EpisodesViewHolder(
            ItemEpisodeBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount() = list.size


    override fun onBindViewHolder(holder: EpisodesViewHolder, position: Int) {
        holder.binding(list[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: ArrayList<EpisodeResult>) {
        this.list = list
        notifyDataSetChanged()

    }

}