package com.example.rickandmorty.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "locationsPageKey")
data class LocationsPageKey (
    @PrimaryKey
    val id: Int?,
    val nextPageUrl: String?
    )
