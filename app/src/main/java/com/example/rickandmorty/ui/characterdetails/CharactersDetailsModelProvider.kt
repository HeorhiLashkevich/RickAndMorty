package com.example.rickandmorty.ui.characterdetails

import com.example.rickandmorty.api.RickAndMortyApi
import com.example.rickandmorty.repository.EpisodesDataStore
import com.example.rickandmorty.ui.base.BaseViewModelFactory
import com.example.rickandmorty.ui.episodes.EpisodesViewModel
import javax.inject.Inject
//
//class CharactersDetailsModelProvider @Inject constructor(
//    private val api: RickAndMortyApi
//
//) :
//    BaseViewModelFactory<CharactersDetailsViewModel>(CharactersDetailsViewModel::class.java) {
//    override fun createViewModel(): CharactersDetailsViewModel {
//        return CharactersDetailsViewModel(api)
//    }
//
//}