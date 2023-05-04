package com.example.rickandmorty.ui.episodes

import com.example.rickandmorty.repository.EpisodesDataStore
import com.example.rickandmorty.ui.base.BaseViewModelFactory
import javax.inject.Inject

class EpisodesModelProvider @Inject constructor(
    private val dataSource: EpisodesDataStore

) :
    BaseViewModelFactory<EpisodesViewModel>(EpisodesViewModel::class.java) {
    override fun createViewModel(): EpisodesViewModel {
        return EpisodesViewModel(dataSource)
    }

}