package com.example.rickandmorty.data.repository

import com.example.rickandmorty.data.model.LocationsEntity
import com.example.rickandmorty.data.model.PagedResponse
import com.example.rickandmorty.data.remove.service.RickAndMortyApi
import retrofit2.Response
import javax.inject.Inject

class LocationsRepository @Inject constructor(
    private val api: RickAndMortyApi
) {
    suspend fun getLocations(page: Int): Response<PagedResponse<LocationsEntity>> {
        return api.getLocations(page = page)
    }
}