package com.example.rickandmorty.data.local.paging.datasource

import androidx.recyclerview.widget.DiffUtil
import com.example.rickandmorty.data.model.CharactersEntity

class CharactersDiffUtil:DiffUtil.ItemCallback<CharactersEntity>() {
    override fun areItemsTheSame(oldItem: CharactersEntity, newItem: CharactersEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CharactersEntity, newItem: CharactersEntity): Boolean {
        return oldItem == newItem
    }
}