package com.example.rickandmorty.repository

import com.example.rickandmorty.api.Locations
import com.example.rickandmorty.api.RickAndMortyApi
import com.example.rickandmorty.di.NetworkController
import retrofit2.Response
import javax.inject.Inject

class LocationsRepository@Inject constructor(
    private val api: RickAndMortyApi
) {
    suspend fun getLocations(count: Int, page: Int): Response<Locations> {
        return api.getLocations(limit = count, page = page)
    }
}