package com.example.rickandmorty.api


import com.example.rickandmorty.ui.characters.COUNT_ITEM
import io.reactivex.Observable
import io.reactivex.Observer
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickAndMortyApi {


    @GET("character")
    suspend fun getCharacters(
        @Query("limit") limit: Int? = COUNT_ITEM,
        @Query("page") page: Int? = 0

    ): Response<Characters>

    @GET("episode")
    suspend fun getEpisodes(
        @Query("limit") limit: Int? = COUNT_ITEM,
        @Query("page") page: Int? = 0
    ): Response<Episodes>


    @GET("character/{id}")
    suspend fun getMultiCharacters(
        @Path("id") id: ArrayList<Int>? = null
    ): Response<ArrayList<CharactersResult>>


    @GET("location")
    suspend fun getLocations(
        @Query("limit") limit: Int? = COUNT_ITEM,
        @Query("page") page: Int? = 0
    ): Response<Locations>

    @GET("episode/{id}")
    suspend fun getEpisode(
        @Path("id") id: Int? = 0
    ): Response<EpisodesResult>


    @GET("character/{id}")
    fun getCharacter(
        @Path("id") id: Int? = 0
    ): Observable<Response<CharactersResult>>

    @GET("episode/{id}")
    fun getMultiEpisodes(
        @Path("id") id: ArrayList<Int>? = null
    ): Observable<Response<ArrayList<EpisodesResult>>>

    @GET("location/{id}")
    fun getLocationForDetails(
        @Path("id") id: Int? = 0
    ): Observable<Response<LocationsResult>>

    @GET("character/{id}")
    fun getMultiCharactersForLocationDetails(
        @Path("id") id: ArrayList<Int>? = null
    ): Observable<Response<ArrayList<CharactersResult>>>


}