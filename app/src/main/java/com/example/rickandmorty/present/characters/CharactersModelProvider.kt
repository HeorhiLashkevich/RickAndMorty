package com.example.rickandmorty.present.characters

import com.example.rickandmorty.data.local.AppDataBase
//import com.example.rickandmorty.data.local.paging.datasource.CharactersDataSource
import com.example.rickandmorty.data.api.RickAndMortyApi
import com.example.rickandmorty.domain.repository.CharacterRepository
import com.example.rickandmorty.present.base.BaseViewModelFactory
import javax.inject.Inject


class CharactersModelProvider @Inject constructor(
    private val api: RickAndMortyApi,
    private val db: AppDataBase,
    private val repo: CharacterRepository
) :
    BaseViewModelFactory<CharactersViewModel>(CharactersViewModel::class.java) {
    override fun createViewModel(): CharactersViewModel {
        return CharactersViewModel(repo ,api, db )
    }

}
