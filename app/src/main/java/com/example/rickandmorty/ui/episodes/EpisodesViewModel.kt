package com.example.rickandmorty.ui.episodes


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.rickandmorty.COUNT_ITEM_EPISODES
import com.example.rickandmorty.api.RickAndMortyApi
import com.example.rickandmorty.repository.CharactersDataSource
import com.example.rickandmorty.repository.EpisodesDataStore
import javax.inject.Inject


class EpisodesViewModel @Inject constructor(
    dataSource: EpisodesDataStore
) : ViewModel() {

    val flow = Pager(
        PagingConfig(pageSize = COUNT_ITEM_EPISODES, initialLoadSize = COUNT_ITEM_EPISODES)
    ) {
        dataSource
    }.flow.cachedIn(viewModelScope)


}









