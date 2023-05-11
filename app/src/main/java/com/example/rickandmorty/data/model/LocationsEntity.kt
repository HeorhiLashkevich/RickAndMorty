package com.example.rickandmorty.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.rickandmorty.data.local.TypeConverter

@Entity(tableName = "locations")

data class LocationsEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "created")
    val created: String,
    @ColumnInfo(name = "dimension")
    val dimension: String,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "residents")
    @TypeConverters(TypeConverter::class)
    val residents: List<String>,
    @ColumnInfo(name = "type")
    val type: String,
    @ColumnInfo(name = "url")
    val url: String,

)
