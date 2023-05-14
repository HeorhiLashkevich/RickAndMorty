package com.example.rickandmorty.present.episodes

import com.example.rickandmorty.data.local.AppDataBase
import com.example.rickandmorty.data.api.RickAndMortyApi
import com.example.rickandmorty.domain.repository.EpisodesRepository
import com.example.rickandmorty.present.base.BaseViewModelFactory
import javax.inject.Inject

class EpisodesModelProvider @Inject constructor(
    private val repo: EpisodesRepository,
    private val api: RickAndMortyApi,
    private val dataBase: AppDataBase


) :
    BaseViewModelFactory<EpisodesViewModel>(EpisodesViewModel::class.java) {
    override fun createViewModel(): EpisodesViewModel {
        return EpisodesViewModel(repo, api, dataBase)
    }

}