package com.example.rickandmorty.repository


import com.example.rickandmorty.api.Episodes
import com.example.rickandmorty.api.RickAndMortyApi

import retrofit2.Response
import javax.inject.Inject

class EpisodesRepository @Inject constructor(
    private val api: RickAndMortyApi
) {
    suspend fun getEpisodes(count: Int, page: Int): Response<Episodes> {
        return api.getEpisodes(limit = count, page = page)
    }
}
