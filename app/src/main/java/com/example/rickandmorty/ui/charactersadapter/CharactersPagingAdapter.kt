package com.example.rickandmorty.ui.charactersadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.example.rickandmort.databinding.ItemCharacterBinding
import com.example.rickandmorty.api.CharactersResult

class CharactersPagingAdapter:PagingDataAdapter<CharactersResult, CharactersViewHolder>(
    CharactersDiffUtil()
) {


    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersViewHolder {
        return CharactersViewHolder(
            ItemCharacterBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

}