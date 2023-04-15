package com.example.rickandmorty.api

data class Episodes(
    val info: InfoEpisode,
    val results: ArrayList<EpisodesResult>
)
data class InfoEpisode(
    val count: Int,
    val next: String,
    val pages: Int,
    val prev: Any
)
data class EpisodesResult(
    val air_date: String,
    val characters: List<String>,
    val created: String,
    val episode: String,
    val id: Int,
    val name: String,
    val url: String
)