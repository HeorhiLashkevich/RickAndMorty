package com.example.rickandmorty.data.model


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.rickandmorty.domain.model.Location
import com.example.rickandmorty.domain.model.Origin
import com.example.rickandmorty.data.local.TypeConverter


@Entity(tableName = "characters")
data class CharactersEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "created")
    val created: String,
    @ColumnInfo(name = "episode")
    @TypeConverters(TypeConverter::class)
    val episode: List<String>,
    @ColumnInfo(name = "origin")
    @TypeConverters(TypeConverter::class)
    val origin: Origin,
    @ColumnInfo(name = "location")
    @TypeConverters(TypeConverter::class)
    val location: Location,
    @ColumnInfo(name = "gender")
    val gender: String,
    @ColumnInfo(name = "image")
    val image: String,
    @ColumnInfo(name = "species")
    val species: String,
    @ColumnInfo(name = "status")
    val status: String,
    @ColumnInfo(name = "type")
    val type: String,
    @ColumnInfo(name = "url")
    val url: String,
)
//    ): CharactersResult {
//    constructor(character: CharactersResult) : this(
//        id = character.id,
//        name = character.name,
//        created = character.created,
//        episode = character.episode,
//        origin = character.origin,
//        location = character.location,
//        gender = character.gender,
//        image = character.image,
//        species = character.species,
//        status = character.status,
//        type = character.type,
//        url = character.url
//    )


