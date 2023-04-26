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
    val episode = MutableLiveData<EpisodesResult>()
    var characters = MutableLiveData<ArrayList<CharactersResult>>()
    var charactersIds = ArrayList<Int>()


    val flow = Pager(
        PagingConfig(pageSize = COUNT_ITEM_EPISODES, initialLoadSize = COUNT_ITEM_EPISODES)
    ) {
        EpisodesDataStore()
    }.flow.cachedIn(viewModelScope)

    fun getEpisode(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = NetworkController.getRickAndMortyApi().getEpisode(id)
            if (response.isSuccessful) {
                charactersIds = response.body()?.let { getCharactersIds(it.characters) }!!
                episode.postValue(response.body())
                getCharacters(charactersIds)

            }
        }
    }


    private fun getCharactersIds(list: List<String>): ArrayList<Int> {
        val charactersIds = arrayListOf<Int>()
        for (i in list.indices) {
            when (list[i].length) {
                (43) -> charactersIds.add(list[i].takeLast(1).toInt())
                (44) -> charactersIds.add(list[i].takeLast(2).toInt())
                (45) -> charactersIds.add(list[i].takeLast(3).toInt())
            }
        }
        return charactersIds
    }

    private fun getCharacters(list: ArrayList<Int>) {
        viewModelScope.launch(Dispatchers.IO) {
            episode.value?.let { getCharactersIds(it.characters) }
            val response = NetworkController.getRickAndMortyApi().getMultiCharacters(list)
            if (response.isSuccessful) {
                characters.postValue(response.body())
            }
        }

    }


}









