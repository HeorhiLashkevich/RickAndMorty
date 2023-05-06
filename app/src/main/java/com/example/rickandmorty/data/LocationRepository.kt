package com.example.rickandmorty.data

import com.example.rickandmorty.api.Locations
import com.example.rickandmorty.data.remove.service.RickAndMortyApi
import retrofit2.Response
import javax.inject.Inject

class LocationsRepository@Inject constructor(
    private val api: RickAndMortyApi
) {
    suspend fun getLocations(count: Int, page: Int): Response<Locations> {
        return api.getLocations(limit = count, page = page)
    }
}