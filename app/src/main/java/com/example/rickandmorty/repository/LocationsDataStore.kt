package com.example.rickandmorty.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.rickandmorty.api.LocationsResult
import javax.inject.Inject

class LocationsDataStore @Inject constructor(
    private val repository: LocationsRepository
) : PagingSource<Int, LocationsResult>() {


    override fun getRefreshKey(state: PagingState<Int, LocationsResult>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, LocationsResult> {
        return try {
            val key = params.key ?: 1
            val response = repository.getLocations(params.loadSize, key)
            val nextKey = key + 1

            LoadResult.Page(
                data = response.body()?.results as ArrayList<LocationsResult>,
                prevKey = null,
                nextKey = nextKey
            )
        } catch (e: java.lang.Exception) {
            LoadResult.Error(e)
        }
    }
}