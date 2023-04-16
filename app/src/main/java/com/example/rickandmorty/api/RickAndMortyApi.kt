package com.example.rickandmorty.api

import com.example.rickandmorty.ui.COUNT_ITEM
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RickAndMortyApi {

    @GET("character")
    suspend fun getCharacters(
        @Query("limit") limit: Int? = COUNT_ITEM,
        @Query("page") page: Int? = 0

    ): Response<Characters>

    @GET("character/")
    suspend fun getCharacter(
        @Query("name") name: String? = null
    ): Response<CharactersResult>

    @GET("episode")
    suspend fun getEpisodes(
        @Query("limit") limit: Int? = COUNT_ITEM,
        @Query("page") page: Int? = 0
    ): Response<Episodes>

    @GET("location")
    suspend fun getLocations(
        @Query("limit") limit: Int? = COUNT_ITEM,
        @Query("page") page: Int? = 0
    ): Response<Locations>

    @GET("episode/")
    suspend fun getEpisode(
        @Query("id") id: Int? = 0
    ): Response<EpisodesResult>


}