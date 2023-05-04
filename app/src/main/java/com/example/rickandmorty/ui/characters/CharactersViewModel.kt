package com.example.rickandmorty.ui.characters


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.rickandmorty.COUNT_ITEM_CHARACTERS
import com.example.rickandmorty.api.CharactersResult
import com.example.rickandmorty.api.RickAndMortyApi
import com.example.rickandmorty.repository.CharactersDataSource


class CharactersViewModel(
    dataSource: CharactersDataSource
) : ViewModel() {

    val flow = Pager(
        PagingConfig(
            pageSize = COUNT_ITEM_CHARACTERS,
            prefetchDistance = 20
        )
    ) {
        dataSource
    }.flow.cachedIn(viewModelScope)


//    fun getBySearchedName(name: String) {
//        viewModelScope.launch(Dispatchers.IO) {
//            val response = NetworkController.getRickAndMortyApi().getSearchedCharactersByName(name)
//            if (response.isSuccessful) {
//                listOfCharacters.postValue(response.body())
//            }
//
//        }
//    }

//    fun getCharactersByGender(gender: String) {
//        viewModelScope.launch(Dispatchers.IO) {
//            listOfCharacters.postValue(NetworkController.getRickAndMortyApi().getCharactersByGender(gender).body())
//        }
//    }
//
//    fun getCharactersByStatus(status: String) {
//        viewModelScope.launch(Dispatchers.IO) {
//            listOfCharacters.postValue(NetworkController.getRickAndMortyApi().getCharactersByStatus(status).body())
//        }
//
//    }
//
//    fun getCharactersByGender(status: String, gender: String) {
//        viewModelScope.launch(Dispatchers.IO) {
//            listOfCharacters.postValue(
//                NetworkController.getRickAndMortyApi().getCharactersByStatusAndGender(status, gender).body()
//            )
//        }
//    }


}