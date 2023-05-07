package com.example.rickandmorty.data.local.paging.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.rickandmorty.data.model.LocationsEntity
import com.example.rickandmorty.data.remove.service.model.LocationsResult
import com.example.rickandmorty.data.repository.LocationsRepository
import javax.inject.Inject

class LocationsDataStore @Inject constructor(
    private val repository: LocationsRepository
) : PagingSource<Int, LocationsEntity>() {


    override fun getRefreshKey(state: PagingState<Int, LocationsEntity>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, LocationsEntity> {
        return try {
            val key = params.key ?: 1
            val response = repository.getLocations(params.loadSize)
            val nextKey = key + 1

            LoadResult.Page(
                data = response.body()?.results as ArrayList<LocationsEntity>,
                prevKey = null,
                nextKey = nextKey
            )
        } catch (e: java.lang.Exception) {
            LoadResult.Error(e)
        }
    }
}