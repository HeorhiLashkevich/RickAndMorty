package com.example.rickandmorty.present.characters


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.example.rickandmorty.data.local.AppDataBase
import com.example.rickandmorty.data.model.CharactersEntity
import com.example.rickandmorty.data.api.RickAndMortyApi
import com.example.rickandmorty.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow


class CharactersViewModel(
    private val repo: CharacterRepository,
    private val service: RickAndMortyApi,
    private val db: AppDataBase,
) : ViewModel() {
    var searchText = MutableLiveData("")
    fun setSearchTerm(query: String){
        searchText.value = query
    }

//     fun getCharactersByName(): Flow<PagingData<CharactersEntity>> {
//        return repo.searchByName(searchText.value).cachedIn(viewModelScope)
//    }
//    fun getCharacters(): Flow<PagingData<CharactersEntity>> {
//        return repo.getCharacters().cachedIn(viewModelScope)
//    }



     fun getCharacters(): Flow<PagingData<CharactersEntity>> {
        return repo.getCharacters().cachedIn(viewModelScope)
    }






//

//    val flow =


}