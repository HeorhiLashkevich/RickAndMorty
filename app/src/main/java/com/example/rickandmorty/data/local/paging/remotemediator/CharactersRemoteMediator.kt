package com.example.rickandmorty.data.local.paging.remotemediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.rickandmorty.data.local.AppDataBase
import com.example.rickandmorty.data.model.RemoteKeys
import com.example.rickandmorty.data.model.CharactersEntity
import com.example.rickandmorty.data.remove.service.RickAndMortyApiService
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class CharactersRemoteMediator(
//    private val query: String,
    private val service: RickAndMortyApiService,
    private val repoDatabase: AppDataBase
) : RemoteMediator<Int, CharactersEntity>() {
    override suspend fun initialize(): InitializeAction {
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }
    override suspend fun load(loadType: LoadType, state: PagingState<Int, CharactersEntity>): MediatorResult {

        val page = when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.nextKey?.minus(1) ?: 1
            }
            LoadType.PREPEND -> {
                val remoteKeys = getRemoteKeyForFirstItem(state)

                val prevKey = remoteKeys?.prevKey
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                prevKey
            }
            LoadType.APPEND -> {
                val remoteKeys = getRemoteKeyForLastItem(state)
                val nextKey = remoteKeys?.nextKey
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                nextKey
            }
        }

//        val apiQuery = query

        try {
//            val apiResponse = service.searchByName (apiQuery, page, state.config.pageSize)
//            val apiResponse = service.searchByName ( page, state.config.pageSize)
            val apiResponse = service.getCharacters ( page = page)

            val chars = apiResponse.body()?.results
            val endOfPaginationReached = chars?.isEmpty()
            repoDatabase.withTransaction {
                // clear all tables in the database
                if (loadType == LoadType.REFRESH) {
                    repoDatabase.getRemoteKeyDao().clearRemoteKeys()
                    repoDatabase.getCharactersDao().clearAll()
                }
                val prevKey = if (page == 1) null else page - 1
                val nextKey = if (endOfPaginationReached == true) null else page + 1
                val keys = chars?.map {
                    RemoteKeys(repoId = it.id, prevKey = prevKey, nextKey = nextKey)
                }
                if (keys != null) {
                    repoDatabase.getRemoteKeyDao(). insertAll(keys)
                }
                if (chars != null) {
                    repoDatabase.getCharactersDao().insertAll(chars)
                }
            }
            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached == true)
        } catch (exception: IOException) {
            return MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            return MediatorResult.Error(exception)
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, CharactersEntity>): RemoteKeys? {

        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { chars ->
                repoDatabase.getRemoteKeyDao().remoteKeysRepoId(chars.id)
            }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, CharactersEntity>): RemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { chars ->
                repoDatabase.getRemoteKeyDao().remoteKeysRepoId(chars.id)
            }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, CharactersEntity>
    ): RemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { charsId ->
                repoDatabase.getRemoteKeyDao().remoteKeysRepoId(charsId)
            }
        }
    }
}




