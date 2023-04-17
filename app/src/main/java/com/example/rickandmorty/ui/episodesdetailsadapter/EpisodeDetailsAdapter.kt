package com.example.rickandmorty.ui.episodesdetailsadapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmort.databinding.ItemCharactersBinding
import com.example.rickandmorty.api.CharactersResult

class EpisodeDetailsAdapter:RecyclerView.Adapter<EpisodeDetailsViewHolder>() {
    private var list = arrayListOf<CharactersResult>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeDetailsViewHolder {
        return EpisodeDetailsViewHolder(
            ItemCharactersBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = list.size


    override fun onBindViewHolder(holder: EpisodeDetailsViewHolder, position: Int) {
        holder.bind(list[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: ArrayList<CharactersResult>) {
        this.list = list
        notifyDataSetChanged()
    }
}