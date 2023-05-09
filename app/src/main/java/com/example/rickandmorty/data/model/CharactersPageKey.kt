package com.example.rickandmorty.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "charactersPageKey")
data class CharactersPageKey(
    @PrimaryKey val id: Int?,
    val nextPageUrl: String?
)