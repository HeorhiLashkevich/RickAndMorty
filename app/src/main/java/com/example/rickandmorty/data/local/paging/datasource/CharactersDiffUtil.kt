package com.example.rickandmorty.data.local.paging.datasource

import androidx.recyclerview.widget.DiffUtil
import com.example.rickandmorty.data.model.CharactersEntity
import com.example.rickandmorty.data.remove.service.model.CharactersResult

class CharactersDiffUtil:DiffUtil.ItemCallback<CharactersResult>() {
    override fun areItemsTheSame(oldItem: CharactersResult, newItem: CharactersResult): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CharactersResult, newItem: CharactersResult): Boolean {
        return oldItem == newItem
    }
}