package com.example.rickandmorty.present.episodes

import com.example.rickandmorty.data.local.AppDataBase
import com.example.rickandmorty.data.remove.service.RickAndMortyApi
import com.example.rickandmorty.ui.base.BaseViewModelFactory
import javax.inject.Inject

class EpisodesModelProvider @Inject constructor(

//    private val dataSource: EpisodesDataSource,
    private val api: RickAndMortyApi,
    private val dataBase: AppDataBase


) :
    BaseViewModelFactory<EpisodesViewModel>(EpisodesViewModel::class.java) {
    override fun createViewModel(): EpisodesViewModel {
        return EpisodesViewModel(api, dataBase)
    }

}