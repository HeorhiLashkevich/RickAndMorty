package com.example.rickandmorty.repository

import com.example.rickandmorty.api.Locations
import com.example.rickandmorty.api.NetworkController
import retrofit2.Response

class LocationsRepository {
    suspend fun getEpisodes(count: Int, page: Int): Response<Locations> {
        return NetworkController.getRickAndMortyApi().getLocations(limit = count, page = page)
    }
}