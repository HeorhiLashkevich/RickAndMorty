package com.example.rickandmorty.data.local.paging.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.rickandmorty.data.model.CharactersEntity
import com.example.rickandmorty.data.remove.service.RickAndMortyApiService
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

//class CharactersDataSource @Inject constructor(
//    private val service: RickAndMortyApiService,
////    private val query: String
//) : PagingSource<Int, CharactersEntity>() {
//
//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharactersEntity> {
//        val position = params.key ?: 1
////        val apiQuery = query
////        return try {
//////            val response = service.searchByName(apiQuery, position, params.loadSize)
////            val response = service.searchByName(position)
////            val repos = response.body()!!.results
////            val nextKey = if (repos.isEmpty()) {
////                null
////            } else {
////                position + (params.loadSize / 20)
////            }
////            LoadResult.Page(
////                data = repos,
////                prevKey = if (position == 1) null else position - 1,
////                nextKey = nextKey
////            )
////        } catch (exception: IOException) {
////            return LoadResult.Error(exception)
////        } catch (exception: HttpException) {
////            return LoadResult.Error(exception)
////        }
//    }
//
//    override fun getRefreshKey(state: PagingState<Int, CharactersEntity>): Int? {
//        return state.anchorPosition?.let { anchorPosition ->
//            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
//                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
//        }
//    }
//
//}


