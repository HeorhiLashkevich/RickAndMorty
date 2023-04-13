package com.example.rickandmort

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmort.api.NetworkController.getRickAndMortyApi
import com.example.rickandmort.api.EpisodeResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class EpisodesViewModel : ViewModel() {

    val listEpisodes = MutableLiveData<ArrayList<EpisodeResult>>()

    fun getAllEpisodes() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = getRickAndMortyApi().getEpisodes()
            if (response.isSuccessful) {
                listEpisodes.postValue(response.body()?.results)
            }
        }
    }
}


