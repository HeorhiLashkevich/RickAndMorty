package com.example.rickandmorty.present.locations

import com.example.rickandmorty.data.local.AppDataBase
import com.example.rickandmorty.data.remove.service.RickAndMortyApiService
import com.example.rickandmorty.data.repository.LocationsRepository
import com.example.rickandmorty.ui.base.BaseViewModelFactory
import javax.inject.Inject

class LocationsModelProvider @Inject constructor(
    private val repository: LocationsRepository,
    private val dataBase: AppDataBase,
    private val api: RickAndMortyApiService
) :
    BaseViewModelFactory<LocationsViewModel>(LocationsViewModel::class.java) {
    override fun createViewModel(): LocationsViewModel {
        return LocationsViewModel(repository, dataBase, api)
    }

}