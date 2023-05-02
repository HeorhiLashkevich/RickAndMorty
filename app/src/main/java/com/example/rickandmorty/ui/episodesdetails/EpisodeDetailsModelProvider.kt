package com.example.rickandmorty.ui.episodesdetails

import com.example.rickandmorty.api.RickAndMortyApi
import com.example.rickandmorty.repository.LocationsDataStore
import com.example.rickandmorty.ui.base.BaseViewModelFactory
import com.example.rickandmorty.ui.locations.LocationsViewModel
import javax.inject.Inject

class EpisodeDetailsModelProvider @Inject constructor(
    private val api: RickAndMortyApi
) :
    BaseViewModelFactory<EpisodeDetailsViewModel>(EpisodeDetailsViewModel::class.java) {
    override fun createViewModel(): EpisodeDetailsViewModel {
        return EpisodeDetailsViewModel(api)
    }

}