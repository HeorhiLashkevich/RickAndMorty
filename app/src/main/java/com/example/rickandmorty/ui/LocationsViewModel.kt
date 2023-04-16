package com.example.rickandmorty.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.rickandmorty.repository.LocationsDataStore


const val COUNT_ITEM_LOCATIONS = 18

class LocationsViewModel : ViewModel() {

    val flow = Pager(
        PagingConfig(pageSize = COUNT_ITEM_LOCATIONS, initialLoadSize = COUNT_ITEM_LOCATIONS)
    ) {
        LocationsDataStore()
    }.flow.cachedIn(viewModelScope)
}