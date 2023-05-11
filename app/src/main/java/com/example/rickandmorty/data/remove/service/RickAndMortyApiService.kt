package com.example.rickandmorty.data.remove.service


import com.example.rickandmorty.api.*
import com.example.rickandmorty.data.model.CharactersEntity
import com.example.rickandmorty.data.model.EpisodeEntity
import com.example.rickandmorty.data.model.LocationsEntity
import com.example.rickandmorty.data.model.PagedResponse
import com.example.rickandmorty.data.remove.service.model.EpisodesResult
import com.example.rickandmorty.data.remove.service.model.LocationsResult
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickAndMortyApiService {
//    @GET("character/?{q}")
//    suspend fun searchByName(
////        @Query("name") query: String,
//        @Query("page") page: Int,
//        @Query("per_page") itemsPerPage: Int
//    ): CharacterSearchResponse
    @GET("character/")
    suspend fun searchByName(
//        @Query("name") query: String,
        @Query("page") page: Int,
//        @Query("per_page") itemsPerPage: Int
    ): Response<PagedResponse<CharactersEntity>>



    @GET("character/{id}")
    fun getCharacter(
        @Path("id") id: Int? = 0
    ): Observable<Response<CharactersResult>>

    @GET("character")
    suspend fun getCharacters(
//        @Query("limit") limit: Int? = COUNT_ITEM_CHARACTERS,
        @Query("page") page: Int? = 0,
        ): Response<PagedResponse<CharactersEntity>>

    @GET("character/?{q}")
    suspend fun getSearchedCharactersByName(
        @Query("q") query: String?,
        @Query("page") page: Int? = 0,
    ): Response<PagedResponse<CharactersEntity>>

    @GET("character/{id}")
    suspend fun getMultiCharacters(
        @Path("id") id: ArrayList<Int>? = null
    ): Response<ArrayList<CharactersResult>>

    @GET("character/{id}")
    fun getMultiCharactersForLocationDetails(
        @Path("id") id: ArrayList<Int>? = null
    ): Observable<Response<ArrayList<CharactersResult>>>

    @GET("character")
    suspend fun getCharactersByStatus(
        @Query("status") status: String
    ): Response<ArrayList<CharactersResult>>

    @GET("character")
    suspend fun getCharactersByGender(
        @Query("gender") gender: String
    ): Response<ArrayList<CharactersResult>>

    @GET("character")
    suspend fun getCharactersByStatusAndGender(
        @Query("status") status: String,
        @Query("gender") gender: String
    ): Response<ArrayList<CharactersResult>>


    //episode

    @GET("episode")
    suspend fun getEpisodes(
//        @Query("limit") limit: Int? = COUNT_ITEM_EPISODES,
        @Query("page") page: Int
    ): Response<PagedResponse<EpisodeEntity>>

    @GET("episode/{id}")
    suspend fun getEpisode(
        @Path("id") id: Int? = 0
    ): Response<EpisodesResult>

    @GET("episode/{id}")
    fun getMultiEpisodes(
        @Path("id") id: ArrayList<Int>? = null
    ): Observable<Response<ArrayList<EpisodesResult>>>


    //location


    @GET("location")
    suspend fun getLocations(
//        @Query("limit") limit: Int? = COUNT_ITEM_LOCATIONS,
        @Query("page") page: Int? = 0
    ): Response<PagedResponse<LocationsEntity>>


    @GET("location/{id}")
    fun getLocationForDetails(
        @Path("id") id: Int? = 0
    ): Observable<Response<LocationsResult>>


}