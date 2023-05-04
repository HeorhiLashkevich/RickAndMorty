package com.example.rickandmorty.ui.characters

import com.example.rickandmorty.repository.CharactersDataSource
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
//class CharactersDetailsModelProvider @Inject constructor(
//
//    private val api: RickAndMortyApi
//) :
//    BaseViewModelFactory<CharactersDetailsViewModel>(CharactersDetailsViewModel::class.java) {
//    override fun createViewModel(): CharactersDetailsViewModel {
//        return CharactersDetailsViewModel(api)
//    }
//
//}