package com.example.rickandmorty.repository


import com.example.rickandmorty.api.Episodes

import com.example.rickandmorty.api.NetworkController
import retrofit2.Response

class EpisodesRepository {
    suspend fun getEpisodes(count: Int, page: Int): Response<Episodes> {
        return NetworkController.getRickAndMortyApi().getEpisodes(limit = count, page = page)
    }
}



