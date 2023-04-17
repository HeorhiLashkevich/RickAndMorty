package com.example.rickandmorty.ui


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
    val episode = MutableLiveData<EpisodesResult>()
    var characters = MutableLiveData<ArrayList<CharactersResult>>()
//    var charactersIds = episode.value?.let { getCharactersIds(it.characters) }


    val flow = Pager(
        PagingConfig(pageSize = COUNT_ITEM_EPISODES, initialLoadSize = COUNT_ITEM_EPISODES)
    ) {
        EpisodesDataStore()
    }.flow.cachedIn(viewModelScope)

    fun getEpisode(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = NetworkController.getRickAndMortyApi().getEpisode(id)
            if (response.isSuccessful) {
                episode.postValue(response.body())
            }
        }
    }

    suspend fun getCharacters() {
        viewModelScope.launch(Dispatchers.IO) {
            val array = arrayListOf<CharactersResult>()
            val response = NetworkController.getRickAndMortyApi().getCharacter(1)
            if (response.isSuccessful) {
                response.body()?.let { array.add(it) }
            }
            characters.postValue(array)
        }

    }


//    private fun getCharactersIds(list: List<String>) {
//        val arrayOfIds = arrayListOf<Int>()
//        for (i in 0..list.size) {
//            when (list[i].length) {
//                (43) -> arrayOfIds.add(list[i].takeLast(1).toInt())
//                (44) -> arrayOfIds.add(list[i].takeLast(2).toInt())
//                (45) -> arrayOfIds.add(list[i].takeLast(3).toInt())
//            }
//        }
//
//
//    }

}









