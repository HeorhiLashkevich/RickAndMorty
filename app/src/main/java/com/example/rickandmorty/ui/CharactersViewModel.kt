package com.example.rickandmorty.ui


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.rickandmorty.repository.CharactersDataSource

const val COUNT_ITEM = 20

class CharactersViewModel : ViewModel() {


    val flow = Pager(
        PagingConfig(pageSize = COUNT_ITEM,
            prefetchDistance = 20
            )

    ) {
        CharactersDataSource()
    }.flow.cachedIn(viewModelScope)


}