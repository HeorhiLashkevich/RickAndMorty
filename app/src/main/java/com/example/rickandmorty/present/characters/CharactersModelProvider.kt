package com.example.rickandmorty.present.characters

import com.example.rickandmorty.data.local.AppDataBase
import com.example.rickandmorty.data.local.paging.datasource.CharactersDataSource
import com.example.rickandmorty.data.remove.service.RickAndMortyApiService
import com.example.rickandmorty.data.repository.CharRepo
import com.example.rickandmorty.ui.base.BaseViewModelFactory
import javax.inject.Inject


class CharactersModelProvider @Inject constructor(
    private val dataSource: CharactersDataSource,
    private val api: RickAndMortyApiService,
    private val db: AppDataBase,
    private val repo: CharRepo
) :
    BaseViewModelFactory<CharactersViewModel>(CharactersViewModel::class.java) {
    override fun createViewModel(): CharactersViewModel {
        return CharactersViewModel(dataSource, repo = repo, api, db)
    }

}
