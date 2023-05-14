package com.example.rickandmorty.data.local.paging.remotemediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.rickandmorty.data.local.AppDataBase
import com.example.rickandmorty.data.mapper.toCharactersEntity
import com.example.rickandmorty.data.model.RemoteKeys
import com.example.rickandmorty.data.model.CharactersEntity
import com.example.rickandmorty.data.api.RickAndMortyApi
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class CharactersRemoteMediator(
    private val service: RickAndMortyApi,
    private val db: AppDataBase,
//    private val name: String?,

    ) : RemoteMediator<Int, CharactersEntity>() {
    override suspend fun initialize(): InitializeAction {
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CharactersEntity>
    ): MediatorResult {
        val page: Int = when(loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.nextKey?.minus(1) ?: 1
            }
            LoadType.PREPEND -> {
                val remoteKeys = getRemoteKeyForFirstItem(state)
                remoteKeys?.prevKey ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
            }
            LoadType.APPEND -> {
                val remoteKeys = getRemoteKeyForLastItem(state)
                remoteKeys?.nextKey ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
            }
        }

        try {
//            val apiResponse = service.searchByName(name, page)
            val apiResponse = service.getCharacters(page = page)

            val chars = apiResponse.body()?.results
            val endOfPaginationReached = chars?.isEmpty()
            db.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    db.getRemoteKeyDao().clearRemoteKeys()
                    db.getCharactersDao().clearAll()
                }
                val prevKey = if (page == 1) null else page - 1
                val nextKey = if (endOfPaginationReached == true) null else page + 1
                val keys = chars?.map {
                    RemoteKeys(repoId = it.id, prevKey = prevKey, nextKey = nextKey)
                }
                if (keys != null) {
                    db.getRemoteKeyDao().insertAll(keys)
                }
                if (chars != null) {
                    val charactersEntity = chars.map {
                        it.toCharactersEntity()
                    }
                    db.getCharactersDao().insertAll(charactersEntity)
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
                db.getRemoteKeyDao().remoteKeysRepoId(chars.id.toLong())
            }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, CharactersEntity>): RemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { chars ->
                db.getRemoteKeyDao().remoteKeysRepoId(chars.id.toLong())
            }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, CharactersEntity>
    ): RemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { charsId ->
                db.getRemoteKeyDao().remoteKeysRepoId(charsId.toLong())
            }
        }
    }

}




