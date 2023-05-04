package com.example.rickandmorty.ui.locationdetails

import com.example.rickandmorty.api.RickAndMortyApi
import com.example.rickandmorty.ui.base.BaseViewModelFactory
import com.example.rickandmorty.ui.characterdetails.CharactersDetailsViewModel
import javax.inject.Inject

class LocationDetailsModelProvider @Inject constructor(
    private val api: RickAndMortyApi
) :
    BaseViewModelFactory<LocationDetailsViewModel>(LocationDetailsViewModel::class.java) {
    override fun createViewModel(): LocationDetailsViewModel {
        return LocationDetailsViewModel(api)
    }

}