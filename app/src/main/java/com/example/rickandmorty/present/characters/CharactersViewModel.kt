package com.example.rickandmorty.present.characters


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.rickandmorty.COUNT_ITEM_CHARACTERS

import com.example.rickandmorty.data.local.model.CharactersEntity


class CharactersViewModel(
    private val dataSource: CharactersDataSource
) : ViewModel() {
    var characters = MutableLiveData<List<CharactersEntity>>()

    val flow = Pager(
        PagingConfig(
            pageSize = COUNT_ITEM_CHARACTERS,
            prefetchDistance = 20
        )
    ) {
        dataSource
    }.flow.cachedIn(viewModelScope)

    fun insert(){
    }
}