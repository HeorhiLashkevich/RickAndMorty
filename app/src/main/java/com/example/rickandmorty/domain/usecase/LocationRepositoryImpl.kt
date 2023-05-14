package com.example.rickandmorty.domain.usecase

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.rickandmorty.data.local.AppDataBase
import com.example.rickandmorty.data.local.paging.remotemediator.LocationsRemoteMediator
import com.example.rickandmorty.data.model.LocationsEntity
import com.example.rickandmorty.data.api.RickAndMortyApi
import com.example.rickandmorty.domain.repository.LocationsRepository
import com.example.rickandmorty.utils.COUNT_ITEM_LOCATIONS
import com.example.rickandmorty.utils.COUNT_LOCATIONS_LOAD_SIZE
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocationsRepositoryImpl @Inject constructor(
    private val service: RickAndMortyApi,
    private val dataBase: AppDataBase,
) : LocationsRepository {
    @OptIn(ExperimentalPagingApi::class)
    override  fun searchByLocationName(
    ): Flow<PagingData<LocationsEntity>> {
        return Pager(
            PagingConfig(
                pageSize = COUNT_ITEM_LOCATIONS,
                initialLoadSize = COUNT_LOCATIONS_LOAD_SIZE,
                prefetchDistance = 0
            ),
            remoteMediator = LocationsRemoteMediator(service, dataBase),
            pagingSourceFactory = { dataBase.getLocationsDao().pagingSource() }
        )
            .flow
    }

}