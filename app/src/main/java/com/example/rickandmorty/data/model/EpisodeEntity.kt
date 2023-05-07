package com.example.rickandmorty.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

//@Entity(tableName = "episodes")
//data class EpisodeEntity(
//    @PrimaryKey val id: Int,
//    val name: String,
//    val airDate: String,
//    val code: String,
//    val characters: List<String>,
//    var page: Int?
//)
@Entity(tableName = "episodes")
data class EpisodeEntity(
    @PrimaryKey val id: Int,
    val name: String,
    @SerializedName("air_date") val airDate: String,
    @SerializedName("code", alternate = ["episode"]) val code: String,
    val characters: List<String>,
    var page: Int?
)

//data class Episodes(
//    val info: InfoEpisode,
//    val results: ArrayList<EpisodesResult>
//)
//data class InfoEpisode(
//    val count: Int,
//    val next: String,
//    val pages: Int,
//    val prev: Any
//)
//data class EpisodesResult(
//    val air_date: String,
//    val characters: List<String>,
//    val created: String,
//    val episode: String,
//    val id: Int,
//    val name: String,
//    val url: String
//)