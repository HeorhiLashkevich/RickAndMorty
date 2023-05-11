package com.example.rickandmorty.present.episodesdetails

import com.example.rickandmorty.data.remove.service.RickAndMortyApiService
import com.example.rickandmorty.ui.base.BaseViewModelFactory
import javax.inject.Inject

class EpisodeDetailsModelProvider @Inject constructor(
    private val api: RickAndMortyApiService
) :
    BaseViewModelFactory<EpisodeDetailsViewModel>(EpisodeDetailsViewModel::class.java) {
    override fun createViewModel(): EpisodeDetailsViewModel {
        return EpisodeDetailsViewModel(api)
    }

}