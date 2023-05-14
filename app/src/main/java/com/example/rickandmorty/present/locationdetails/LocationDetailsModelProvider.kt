package com.example.rickandmorty.present.locationdetails

import com.example.rickandmorty.data.api.RickAndMortyApi
import com.example.rickandmorty.present.base.BaseViewModelFactory
import javax.inject.Inject

class LocationDetailsModelProvider @Inject constructor(
    private val api: RickAndMortyApi
) :
    BaseViewModelFactory<LocationDetailsViewModel>(
        LocationDetailsViewModel::class.java) {
    override fun createViewModel(): LocationDetailsViewModel {
        return LocationDetailsViewModel(
            api
        )
    }

}