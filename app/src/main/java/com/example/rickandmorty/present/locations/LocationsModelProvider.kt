package com.example.rickandmorty.present.locations

import com.example.rickandmorty.data.local.AppDataBase
import com.example.rickandmorty.data.local.paging.datasource.LocationsDataStore
import com.example.rickandmorty.data.remove.service.RickAndMortyApi
import com.example.rickandmorty.ui.base.BaseViewModelFactory
import javax.inject.Inject

class LocationsModelProvider @Inject constructor(
    private val dataSource: LocationsDataStore,
    private val dataBase: AppDataBase,
    private val api: RickAndMortyApi
) :
    BaseViewModelFactory<LocationsViewModel>(LocationsViewModel::class.java) {
    override fun createViewModel(): LocationsViewModel {
        return LocationsViewModel(dataSource, dataBase, api)
    }

}