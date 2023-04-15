package com.example.rickandmorty.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.rickandmorty.api.CharactersResult

class CharactersDataSource : PagingSource<Int, CharactersResult>() {

    private val repository = CharactersRepository()


    override fun getRefreshKey(state: PagingState<Int, CharactersResult>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharactersResult> {

        return try {
            val key = params.key ?: 0
            val response = repository.getCharacters(params.loadSize, key)
            val nextKey = key + 1

            LoadResult.Page(
                data = response.body()?.results as ArrayList<CharactersResult>,
                prevKey = null,
                nextKey = nextKey
            )
        } catch (e: java.lang.Exception) {


            LoadResult.Error(e)
        }
    }

}