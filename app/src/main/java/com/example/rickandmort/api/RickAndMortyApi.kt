package com.example.rickandmort.api

import retrofit2.Response
import retrofit2.http.GET

interface RickAndMortyApi {

    @GET("character")
    suspend fun getCharacter(): Response<Characters>

    @GET("episode")
    suspend fun getEpisodes(): Response<Episodes>


}