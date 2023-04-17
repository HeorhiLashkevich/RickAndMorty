package com.example.rickandmorty.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.api.CharactersResult

import com.example.rickandmorty.api.EpisodesResult
import com.example.rickandmorty.api.NetworkController
import com.example.rickandmorty.api.RickAndMortyApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EpisodeDetailsViewModel(
) : ViewModel() {
    val episode = MutableLiveData<EpisodesResult>()
    val characters = MutableLiveData<CharactersResult>()
    var idFromEpisodes = 0


//    fun getEpisode() {
//        viewModelScope.launch(Dispatchers.IO) {
//            val response = NetworkController.getRickAndMortyApi().getEpisode(idFromEpisodes)
//            if (response.isSuccessful) {
//                episode.postValue(response.body())
//            }
//        }
//    }
//    fun setIdFromEpisode(id: Int) {
//        idFromEpisodes = id
//    }
//    fun getCharactersByName(name: String) {
//        viewModelScope.launch(Dispatchers.IO) {
//            val response = api.getCharacter(name)
//            if (response.isSuccessful) {
//                characters.postValue(response.body())
//            }
//        }
//    }


}
