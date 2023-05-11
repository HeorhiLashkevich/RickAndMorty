package com.example.rickandmorty.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName

@Entity(tableName = "episodes")
data class EpisodeEntity(
    @PrimaryKey val id: Long,
    @ColumnInfo("airDate")
    val air_date: String,
    @ColumnInfo("characters")
    @TypeConverters(TypeConverters::class)
    val characters: List<String>,
    @ColumnInfo("created")
    val created: String,
    @ColumnInfo("episode")
    val episode: String,
    @ColumnInfo("name")
    val name: String,
    @ColumnInfo("url")
    val url: String
)
//@Entity(tableName = "episodes")
//data class EpisodeEntity(
//    @PrimaryKey val id: Long,
//    val name: String,
//    @SerializedName("air_date") val airDate: String,
//    @SerializedName("code", alternate = ["episode"]) val code: String,
//    val characters: List<String>,
////    var page: Int?
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