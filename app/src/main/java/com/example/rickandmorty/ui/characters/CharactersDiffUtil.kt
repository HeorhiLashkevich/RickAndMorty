package com.example.rickandmorty.ui.characters

import androidx.recyclerview.widget.DiffUtil
import com.example.rickandmorty.api.CharactersResult

class CharactersDiffUtil:DiffUtil.ItemCallback<CharactersResult>() {
    override fun areItemsTheSame(oldItem: CharactersResult, newItem: CharactersResult): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CharactersResult, newItem: CharactersResult): Boolean {
        return oldItem == newItem
    }
}