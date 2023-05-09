package com.example.rickandmorty.present.characters

import com.example.rickandmorty.data.local.AppDataBase
import com.example.rickandmorty.data.remove.service.RickAndMortyApi
import com.example.rickandmorty.ui.base.BaseViewModelFactory
import javax.inject.Inject


class CharactersModelProvider @Inject constructor(
//    private val dataSource: CharactersDataSource,
    private val api: RickAndMortyApi,
    private val db: AppDataBase
) :
    BaseViewModelFactory<CharactersViewModel>(CharactersViewModel::class.java) {
    override fun createViewModel(): CharactersViewModel {
        return CharactersViewModel(api, db)
    }

}
