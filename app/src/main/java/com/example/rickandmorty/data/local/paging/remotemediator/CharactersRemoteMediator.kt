package com.example.rickandmorty.data.local.paging.remotemediator

import android.net.Uri
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.rickandmorty.data.local.AppDataBase
import com.example.rickandmorty.data.model.CharactersEntity
import com.example.rickandmorty.data.model.CharactersPageKey
import com.example.rickandmorty.data.remove.service.RickAndMortyApi

@OptIn(ExperimentalPagingApi::class)
class CharactersRemoteMediator(val service: RickAndMortyApi, val db: AppDataBase) :
    RemoteMediator<Int, CharactersEntity>() {
    private val characterDao = db.getCharactersDao()
    private val keyDao = db.getCharactersPageKeyDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CharactersEntity>
    ): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND ->
                    return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    val remoteKey: CharactersPageKey? = db.withTransaction {
                        if (lastItem?.id != null) {
                            keyDao.getNextPageKey(lastItem.id)
                        } else null
                    }

                    if (remoteKey?.nextPageUrl == null) {
                        return MediatorResult.Success(
                            endOfPaginationReached = true
                        )
                    }

                    val uri = Uri.parse(remoteKey.nextPageUrl)
                    val nextPageQuery = uri.getQueryParameter("page")
                    nextPageQuery?.toInt()
                }
            }

            val response = service.getCharacters(loadKey ?: 1)
            val resBody = response.body()
            val pageInfo = resBody?.pageInfo
            val episodes = resBody?.results
            db.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    characterDao.clearAll()
                    keyDao.clearAll()
                }
                episodes?.forEach {
                    it.page = loadKey
                    keyDao.insertOrReplace(CharactersPageKey(it.id, pageInfo?.next))
                }
                episodes?.let { characterDao.insertAll(it) }
            }

            MediatorResult.Success(
                endOfPaginationReached = resBody?.pageInfo?.next == null
            )
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }

}


//
//@Suppress("UNCHECKED_CAST")
//@OptIn(ExperimentalPagingApi::class)
//class CharactersRemoteMediator(val service: RickAndMortyApi, val db: AppDataBase) :
//    RemoteMediator<Int, CharactersEntity>() {
////    private val charactersDao = db.getCharactersDao()
////    private val keyDao = db.getPageKeyDao()
//    override suspend fun initialize(): InitializeAction {
//        return InitializeAction.LAUNCH_INITIAL_REFRESH
//    }
//    override suspend fun load(loadType: LoadType, state: PagingState<Int, CharactersEntity>): MediatorResult {
//
//        val page = when (loadType) {
//            LoadType.REFRESH -> {
//                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
//                remoteKeys?.nextKey?.minus(1) ?: CHARACTERS_STARTING_PAGE_INDEX
//            }
//            LoadType.PREPEND -> {
//                val remoteKeys = getRemoteKeyForFirstItem(state)
//                val prevKey = remoteKeys?.prevKey
//                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
//                prevKey
//            }
//            LoadType.APPEND -> {
//                val remoteKeys = getRemoteKeyForLastItem(state)
//                val nextKey = remoteKeys?.nextKey
//                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
//                nextKey
//            }
//        }
//
//
//        try {
//            val apiResponse = service.getCharacters(page)
//
//            val repos = apiResponse.body()?.results
//            val endOfPaginationReached = repos?.isEmpty()
//            db.withTransaction {
//                if (loadType == LoadType.REFRESH) {
//                    db.getRemoteKeyDao().clearRemoteKeys()
//                    db.getCharactersDao().pagingSource()
//                }
//                val prevKey = if (page == CHARACTERS_STARTING_PAGE_INDEX) null else page - 1
//                val nextKey = if (endOfPaginationReached == true) null else page + 1
//                val keys = repos?.map {
//                    it.id?.let { it1 -> RemoteKeys(repoId = it1.toLong(), prevKey = prevKey, nextKey = nextKey) }
//                }
//                if (keys != null) {
//                    db.getRemoteKeyDao().insertAll(keys as List<RemoteKeys>)
//                }
//                if (repos != null) {
//                    db.getCharactersDao().insertAll(repos)
//                }
//            }
//            return endOfPaginationReached?.let { MediatorResult.Success(endOfPaginationReached = it) }!!
//        } catch (exception: IOException) {
//            return MediatorResult.Error(exception)
//        } catch (exception: HttpException) {
//            return MediatorResult.Error(exception)
//        }
//    }
//
//    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, CharactersEntity>): RemoteKeys? {
//        return state.pages.lastOrNull() { it.data.isNotEmpty() }?.data?.lastOrNull()
//            ?.let { repo ->
//                repo.id?.let { db.getRemoteKeyDao().remoteKeysRepoId(it.toLong() + 1) }
//            }
//    }
//
//    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, CharactersEntity>): RemoteKeys? {
//        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
//            ?.let { repo ->
//                repo.id?.let { db.getRemoteKeyDao() .remoteKeysRepoId(it.toLong()) }
//            }
//    }
//
//    private suspend fun getRemoteKeyClosestToCurrentPosition(
//        state: PagingState<Int, CharactersEntity>
//    ): RemoteKeys? {
//        return state.anchorPosition?.let { position ->
//            state.closestItemToPosition(position)?.id?.let { repoId ->
//                db.getRemoteKeyDao().remoteKeysRepoId(repoId.toLong())
//            }
//        }
//    }
//}

//    override suspend fun load(
//        loadType: LoadType,
//        state: PagingState<Int, CharactersEntity>
//    ): MediatorResult {
//        return try {
//            val loadKey = when (loadType) {
//                LoadType.REFRESH -> 1
//                LoadType.PREPEND ->
//                    return MediatorResult.Success(endOfPaginationReached = true)
//                LoadType.APPEND -> {
//                    val lastItem = state.lastItemOrNull()
//                    val remoteKey: PageKey? = db.withTransaction {
//                        if (lastItem?.id != null) {
//                            keyDao.getNextPageKey(lastItem.id)
//                        } else null
//                    }
//
//                    if (remoteKey?.nextPageUrl == null) {
//                        return MediatorResult.Success(
//                            endOfPaginationReached = true
//                        )
//                    }
//
//                    val uri = Uri.parse(remoteKey.nextPageUrl)
//                    val nextPageQuery = uri.getQueryParameter("page")
//                    nextPageQuery?.toInt()
//                }
//            }
//
//            val response = service.getCharacters(loadKey ?: 1)
//            val resBody = response.body()
//            val pageInfo = resBody?.pageInfo
//            val characters = resBody?.results
//            db.withTransaction {
//                if (loadType == LoadType.REFRESH) {
//                    charactersDao.clearAll()
//                    keyDao.clearAll()
//                }
//                characters?.forEach {
//                    it.page = loadKey
//                    keyDao.insertOrReplace(PageKey(it.id, pageInfo?.next))
//                }
//                characters?.let { charactersDao.insertAll(it) }
//            }
//
//            MediatorResult.Success(
//                endOfPaginationReached = resBody?.pageInfo?.next == null
//            )
//        } catch (e: Exception) {
//            MediatorResult.Error(e)
//        }
//    }
//}

