package com.example.rickandmort.api

import retrofit2.Response
import retrofit2.http.GET

interface RickAndMortyApi {

    @GET("character")
    suspend fun getCharacters(): Response<Characters>

    @GET("episode")
    suspend fun getEpisodes(): Response<Episodes>

    @GET("episode")
    suspend fun getLocations(): Response<Locations>


}