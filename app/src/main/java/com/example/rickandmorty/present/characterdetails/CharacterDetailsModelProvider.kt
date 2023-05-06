package com.example.rickandmorty.present.characterdetails

import com.example.rickandmorty.data.remove.service.RickAndMortyApi
import com.example.rickandmorty.ui.base.BaseViewModelFactory
import javax.inject.Inject

class CharactersDetailsModelProvider @Inject constructor(
    private val api: RickAndMortyApi
) :
    BaseViewModelFactory<CharactersDetailsViewModel>(
        CharactersDetailsViewModel::class.java) {
    override fun createViewModel(): CharactersDetailsViewModel {
        return CharactersDetailsViewModel(
            api
        )
    }

}