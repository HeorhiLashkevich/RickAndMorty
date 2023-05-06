package com.example.rickandmorty.present.episodes

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