package com.example.rickandmorty.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.rickandmorty.api.EpisodesResult
import javax.inject.Inject

class EpisodesDataStore@Inject constructor(
    private val repository: EpisodesRepository
) : PagingSource<Int, EpisodesResult>() {



    override fun getRefreshKey(state: PagingState<Int, EpisodesResult>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, EpisodesResult> {
        return try {
            val key = params.key ?: 1
            val response = repository.getEpisodes(params.loadSize, key)
            val nextKey = key + 1

            LoadResult.Page(
                data = response.body()?.results as ArrayList<EpisodesResult>,
                prevKey = null,
                nextKey = nextKey
            )
        } catch (e: java.lang.Exception) {
            LoadResult.Error(e)
        }
    }
}