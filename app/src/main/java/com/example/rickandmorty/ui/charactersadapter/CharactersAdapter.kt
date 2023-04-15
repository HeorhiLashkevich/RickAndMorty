package com.example.rickandmorty.ui.charactersadapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmort.databinding.ItemCharacterBinding
import com.example.rickandmorty.api.CharactersResult

class CharactersAdapter() : RecyclerView.Adapter<CharactersViewHolder>() {
    private var list = arrayListOf<CharactersResult>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersViewHolder {
        return CharactersViewHolder(
            ItemCharacterBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount() = list.size


    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {
        holder.bind(list[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: ArrayList<CharactersResult>) {
        this.list = list
        notifyDataSetChanged()

    }

}