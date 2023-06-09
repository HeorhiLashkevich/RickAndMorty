package com.example.rickandmorty.domain.repository

import androidx.paging.PagingData
import com.example.rickandmorty.data.model.LocationsEntity
import kotlinx.coroutines.flow.Flow

interface LocationsRepository {

     fun searchByLocationName()
            : Flow<PagingData<LocationsEntity>>
}