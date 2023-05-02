package com.example.rickandmorty.ui.characters

import com.example.rickandmorty.api.RickAndMortyApi
import com.example.rickandmorty.repository.CharactersDataSource
import com.example.rickandmorty.ui.base.BaseViewModelFactory
import javax.inject.Inject



class CharactersModelProvider @Inject constructor(
    private val dataSource: CharactersDataSource,
    private val api: RickAndMortyApi
) :
    BaseViewModelFactory<CharactersViewModel>(CharactersViewModel::class.java) {
    override fun createViewModel(): CharactersViewModel {
        return CharactersViewModel(dataSource)
    }

}