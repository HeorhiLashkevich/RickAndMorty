package com.example.rickandmorty.domain.model

data class Characters(
    val info: Info,
    val results: ArrayList<CharactersResult>
)

data class Info(
    val count: Int,
    val next: String,
    var pages: Int,
    val prev: Any
)
data class Location(
    val name: String,
    val url: String
)
data class Origin(
    val name: String,
    val url: String
)
data class CharactersResult(
    val id: Int,
    val created: String,
    val episode: List<String>,
    val gender: String,
    val image: String,
    val location: Location,
    val name: String,
    val origin: Origin,
    val species: String,
    val status: String,
    val type: String,
    val url: String
)

