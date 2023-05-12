package com.example.rickandmorty.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.rickandmorty.data.local.AppDataBase
import com.example.rickandmorty.data.local.paging.remotemediator.LocationsRemoteMediator
import com.example.rickandmorty.data.model.LocationsEntity
import com.example.rickandmorty.data.remove.service.RickAndMortyApiService
import com.example.rickandmorty.utils.COUNT_ITEM_LOCATIONS
import com.example.rickandmorty.utils.COUNT_LOCATIONS_LOAD_SIZE
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocationsRepositoryImpl @Inject constructor(
    private val service: RickAndMortyApiService,
    private val dataBase: AppDataBase,
) : LocationsRepository {
    @OptIn(ExperimentalPagingApi::class)
    override suspend fun searchByLocationName(
    ): Flow<PagingData<LocationsEntity>> {
        return Pager(
            PagingConfig(
                pageSize = COUNT_ITEM_LOCATIONS,
                initialLoadSize = COUNT_LOCATIONS_LOAD_SIZE,
                prefetchDistance = COUNT_LOCATIONS_LOAD_SIZE
            ),
            remoteMediator = LocationsRemoteMediator(service, dataBase),
            pagingSourceFactory = { dataBase.getLocationsDao().pagingSource() }
        )
            .flow
    }

}