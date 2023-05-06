package com.example.rickandmorty.present.episodesdetails

import com.example.rickandmorty.data.remove.service.RickAndMortyApi
import com.example.rickandmorty.ui.base.BaseViewModelFactory
import javax.inject.Inject

class EpisodeDetailsModelProvider @Inject constructor(
    private val api: RickAndMortyApi
) :
    BaseViewModelFactory<EpisodeDetailsViewModel>(EpisodeDetailsViewModel::class.java) {
    override fun createViewModel(): EpisodeDetailsViewModel {
        return EpisodeDetailsViewModel(api)
    }

}