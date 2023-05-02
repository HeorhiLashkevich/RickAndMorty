package com.example.rickandmorty.ui.episodesdetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.api.CharactersResult

import com.example.rickandmorty.api.EpisodesResult
import com.example.rickandmorty.api.RickAndMortyApi
import com.example.rickandmorty.di.NetworkController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class EpisodeDetailsViewModel(

    private val api: RickAndMortyApi
) : ViewModel() {

    var characters = MutableLiveData<ArrayList<CharactersResult>>()
    var charactersIds = ArrayList<Int>()
    val episode = MutableLiveData<EpisodesResult>()

    fun getEpisode(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = api.getEpisode(id)
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
            val response = api.getMultiCharacters(list)
            if (response.isSuccessful) {
                characters.postValue(response.body())
            }
        }

    }


}
