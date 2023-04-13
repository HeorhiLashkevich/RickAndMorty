package com.example.rickandmort.api

data class LocationFromLocation(
    val info: Info,
    val results: List<ResultLocation>
)
data class InfoLocation(
    val count: Int,
    val next: String,
    val pages: Int,
    val prev: Any
)
data class ResultLocation(
    val created: String,
    val dimension: String,
    val id: Int,
    val name: String,
    val residents: List<String>,
    val type: String,
    val url: String
)