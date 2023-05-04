package com.example.rickandmorty.ui.locations

import com.example.rickandmorty.repository.LocationsDataStore
import com.example.rickandmorty.ui.base.BaseViewModelFactory
import javax.inject.Inject

class LocationsModelProvider @Inject constructor(
    private val dataSource: LocationsDataStore
) :
    BaseViewModelFactory<LocationsViewModel>(LocationsViewModel::class.java) {
    override fun createViewModel(): LocationsViewModel {
        return LocationsViewModel(dataSource)
    }

}