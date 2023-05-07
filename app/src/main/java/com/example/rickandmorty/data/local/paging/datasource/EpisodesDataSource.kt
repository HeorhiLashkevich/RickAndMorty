package com.example.rickandmorty.data.local.paging.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.rickandmorty.data.model.EpisodeEntity
import com.example.rickandmorty.data.repository.EpisodesRepository
import javax.inject.Inject

class EpisodesDataSource@Inject constructor(
    private val repository: EpisodesRepository
) : PagingSource<Int, EpisodeEntity>() {

    override fun getRefreshKey(state: PagingState<Int, EpisodeEntity>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, EpisodeEntity> {
        return try {
            val key = params.key ?: 1
            val response = repository.getEpisodes(params.loadSize, key)
            val nextKey = key + 1

            LoadResult.Page(
                data = response.body()?.results as ArrayList<EpisodeEntity>,
                prevKey = null,
                nextKey = nextKey
            )
        } catch (e: java.lang.Exception) {
            LoadResult.Error(e)
        }
    }
}