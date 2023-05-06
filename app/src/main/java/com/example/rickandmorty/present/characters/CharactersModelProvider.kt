package com.example.rickandmorty.present.characters

import com.example.rickandmorty.ui.base.BaseViewModelFactory
import javax.inject.Inject



class CharactersModelProvider @Inject constructor(
    private val dataSource: CharactersDataSource,
) :
    BaseViewModelFactory<CharactersViewModel>(CharactersViewModel::class.java) {
    override fun createViewModel(): CharactersViewModel {
        return CharactersViewModel(dataSource)
    }

}
