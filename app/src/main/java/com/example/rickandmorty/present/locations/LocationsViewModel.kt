package com.example.rickandmorty.present.locations

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.example.rickandmorty.utils.COUNT_ITEM_LOCATIONS
import com.example.rickandmorty.data.local.AppDataBase
import com.example.rickandmorty.data.local.paging.remotemediator.LocationsRemoteMediator
import com.example.rickandmorty.data.model.EpisodeEntity
import com.example.rickandmorty.data.model.LocationsEntity
import com.example.rickandmorty.data.remove.service.RickAndMortyApiService
import com.example.rickandmorty.data.repository.EpisodesRepository
import com.example.rickandmorty.data.repository.LocationsRepository
import com.example.rickandmorty.utils.COUNT_LOCATIONS_LOAD_SIZE
import kotlinx.coroutines.flow.Flow


class LocationsViewModel(
    private val repo: LocationsRepository,
    private val db: AppDataBase,
    private val api: RickAndMortyApiService
) : ViewModel() {

    suspend fun searchLocations(): Flow<PagingData<LocationsEntity>> {
        return repo.searchByLocationName().cachedIn(viewModelScope)
    }


//    @OptIn(ExperimentalPagingApi::class)
//    val flow = Pager(
//        PagingConfig(
//            pageSize = COUNT_ITEM_LOCATIONS,
//            initialLoadSize = COUNT_LOCATIONS_LOAD_SIZE,
//            prefetchDistance = COUNT_LOCATIONS_LOAD_SIZE
//        ),
//        remoteMediator = LocationsRemoteMediator(api, db)
//    ) {
//        db.getLocationsDao().pagingSource()
//
////        dataSource
//    }.flow.cachedIn(viewModelScope)
}