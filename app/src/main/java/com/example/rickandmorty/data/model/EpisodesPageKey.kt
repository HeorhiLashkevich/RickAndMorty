package com.example.rickandmorty.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "episodesPageKey")
data class EpisodesPageKey(
    @PrimaryKey
    val id: Int?,
    val nextPageUrl: String?
)
