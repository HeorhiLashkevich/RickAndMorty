package com.example.rickandmorty.ui.characters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.example.rickandmort.databinding.ItemCharactersBinding
import com.example.rickandmorty.api.CharactersResult

class CharactersPagingAdapter(
    private val onCharacterClick: (id: Int) -> Unit
) : PagingDataAdapter<CharactersResult, CharactersViewHolder>(CharactersDiffUtil()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersViewHolder {
        return CharactersViewHolder(
            ItemCharactersBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {

        getItem(position)?.let { id ->
            holder.bind(id)
            holder.itemView.setOnClickListener {
                onCharacterClick(id.id)
            }
        }
    }

}