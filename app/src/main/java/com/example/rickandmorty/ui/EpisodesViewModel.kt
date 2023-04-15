package com.example.rickandmorty.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.rickandmorty.repository.EpisodesDataStore


class EpisodesViewModel : ViewModel() {


    val flow = Pager(
        PagingConfig(pageSize = COUNT_ITEM, initialLoadSize = COUNT_ITEM)
    ) {
        EpisodesDataStore()
    }.flow.cachedIn(viewModelScope)


}



