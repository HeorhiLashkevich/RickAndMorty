package com.example.rickandmorty.ui.episodes


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.rickandmorty.api.CharactersResult
import com.example.rickandmorty.api.EpisodesResult
import com.example.rickandmorty.api.NetworkController

import com.example.rickandmorty.repository.EpisodesDataStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


const val COUNT_ITEM_EPISODES = 11

class EpisodesViewModel(
) : ViewModel() {
//    val episode = MutableLiveData<EpisodesResult>()


    val flow = Pager(
        PagingConfig(pageSize = COUNT_ITEM_EPISODES, initialLoadSize = COUNT_ITEM_EPISODES)
    ) {
        EpisodesDataStore()
    }.flow.cachedIn(viewModelScope)

//    fun getEpisode(id: Int) {
//        viewModelScope.launch(Dispatchers.IO) {
//            val response = NetworkController.getRickAndMortyApi().getEpisode(id)
//            if (response.isSuccessful) {
//                episode.postValue(response.body())
//
//            }
//        }
//    }


}









