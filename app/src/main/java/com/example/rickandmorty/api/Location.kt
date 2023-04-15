package com.example.rickandmorty.api

data class Locations(
    val info: Info,
    val results: ArrayList<LocationsResult>
)
data class InfoLocation(
    val count: Int,
    val next: String,
    val pages: Int,
    val prev: Any
)
data class LocationsResult(
    val created: String,
    val dimension: String,
    val id: Int,
    val name: String,
    val residents: List<String>,
    val type: String,
    val url: String
)