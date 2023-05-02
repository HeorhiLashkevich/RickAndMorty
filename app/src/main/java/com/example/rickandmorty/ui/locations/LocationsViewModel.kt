package com.example.rickandmorty.ui.locations

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.rickandmorty.COUNT_ITEM_LOCATIONS
import com.example.rickandmorty.repository.EpisodesDataStore
import com.example.rickandmorty.repository.LocationsDataStore



class LocationsViewModel(
    dataSource: LocationsDataStore
) : ViewModel() {

    val flow = Pager(
        PagingConfig(pageSize = COUNT_ITEM_LOCATIONS, initialLoadSize = COUNT_ITEM_LOCATIONS)
    ) {
        dataSource
    }.flow.cachedIn(viewModelScope)
}