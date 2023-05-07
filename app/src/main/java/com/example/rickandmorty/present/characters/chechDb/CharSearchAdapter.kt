package com.example.rickandmorty.present.characters.chechDb

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmort.databinding.ItemCharactersBinding
import com.example.rickandmorty.api.CharactersResult

class CharSearchAdapter (
) : RecyclerView.Adapter<CharSearchViewHolder>() {
    private var list = listOf<CharactersResult>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharSearchViewHolder {
        return CharSearchViewHolder(
            ItemCharactersBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
    override fun getItemCount() = list.size


    override fun onBindViewHolder(holder: CharSearchViewHolder, position: Int) {
        holder.bind(list[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<CharactersResult>) {
        this.list = list
        notifyDataSetChanged()
    }
}