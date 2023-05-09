package com.example.rickandmorty.present.locations

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.rickandmorty.utils.COUNT_ITEM_LOCATIONS
import com.example.rickandmorty.data.local.AppDataBase
import com.example.rickandmorty.data.local.paging.datasource.LocationsDataStore
import com.example.rickandmorty.data.local.paging.remotemediator.LocationsRemoteMediator
import com.example.rickandmorty.data.remove.service.RickAndMortyApi


class LocationsViewModel(
    private val dataSource: LocationsDataStore,
   private val db: AppDataBase,
   private  val api: RickAndMortyApi
) : ViewModel() {

    @OptIn(ExperimentalPagingApi::class)
    val flow = Pager(
        PagingConfig(pageSize = COUNT_ITEM_LOCATIONS,
            initialLoadSize = 40,
            prefetchDistance = 40
        ),
        remoteMediator = LocationsRemoteMediator(api, db)
    ) {
        db.getLocationsDao().pagingSource()

//        dataSource
    }.flow.cachedIn(viewModelScope)
}