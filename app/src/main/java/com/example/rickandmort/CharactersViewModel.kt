package com.example.rickandmort

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmort.api.CharactersResult
import com.example.rickandmort.api.NetworkController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CharactersViewModel : ViewModel() {

    val listCharacters = MutableLiveData<ArrayList<CharactersResult>>()

    fun getAllCharacters() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = NetworkController.getRickAndMortyApi().getCharacters()
            if (response.isSuccessful) {
                listCharacters.postValue(response.body()?.results)
            }
        }
    }
}