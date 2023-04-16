package com.example.rickandmorty.ui


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn

import com.example.rickandmorty.repository.EpisodesDataStore


const val COUNT_ITEM_EPISODES = 11

class EpisodesViewModel(
//    val api: RickAndMortyApi
) : ViewModel() {
//    val episode = MutableLiveData<EpisodesResult>()

    val flow = Pager(
        PagingConfig(pageSize = COUNT_ITEM_EPISODES, initialLoadSize = COUNT_ITEM_EPISODES)
    ) {
        EpisodesDataStore()
    }.flow.cachedIn(viewModelScope)



//    fun getEpisode(id: Int) {
//        viewModelScope.launch(Dispatchers.IO) {
//            val response = api.getEpisode(id)
//            if (response.isSuccessful) {
//                episode.postValue(response.body())
//            }
//        }
//    }



}



