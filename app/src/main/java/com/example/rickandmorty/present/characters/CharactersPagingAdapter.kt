package com.example.rickandmorty.present.characters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.example.rickandmort.databinding.ItemCharactersBinding
import com.example.rickandmorty.data.local.paging.datasource.CharactersDiffUtil
import com.example.rickandmorty.data.model.CharactersEntity

class CharactersPagingAdapter(
    private val onCharacterClick: (id: Int) -> Unit
) : PagingDataAdapter<CharactersEntity, CharactersViewHolder>(CharactersDiffUtil()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersViewHolder {
        return CharactersViewHolder(
            ItemCharactersBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {
        holder.setIsRecyclable(false)
        getItem(position)?.let { id ->
            holder.bind(id)

            holder.itemView.setOnClickListener {
                onCharacterClick(id.id!!.toInt())
            }
        }
    }

}