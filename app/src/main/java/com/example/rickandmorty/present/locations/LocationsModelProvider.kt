package com.example.rickandmorty.present.locations

import com.example.rickandmorty.data.local.AppDataBase
import com.example.rickandmorty.data.api.RickAndMortyApi
import com.example.rickandmorty.domain.repository.LocationsRepository
import com.example.rickandmorty.present.base.BaseViewModelFactory
import javax.inject.Inject

class LocationsModelProvider @Inject constructor(
    private val repository: LocationsRepository,
    private val dataBase: AppDataBase,
    private val api: RickAndMortyApi
) :
    BaseViewModelFactory<LocationsViewModel>(LocationsViewModel::class.java) {
    override fun createViewModel(): LocationsViewModel {
        return LocationsViewModel(repository)
    }

}