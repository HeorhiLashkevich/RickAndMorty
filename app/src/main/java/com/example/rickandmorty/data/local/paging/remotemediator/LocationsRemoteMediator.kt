package com.example.rickandmorty.data.local.paging.remotemediator

import android.net.Uri
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.rickandmorty.data.local.AppDataBase
import com.example.rickandmorty.data.model.LocationsEntity
import com.example.rickandmorty.data.model.CharactersPageKey
import com.example.rickandmorty.data.model.LocationsPageKey
import com.example.rickandmorty.data.remove.service.RickAndMortyApi

@OptIn(ExperimentalPagingApi::class)
class LocationsRemoteMediator(val service: RickAndMortyApi, val db: AppDataBase) :
    RemoteMediator<Int, LocationsEntity>() {
    private val locationsDao = db.getLocationsDao()
    private val keyDao = db.getLocationsPageKeyDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, LocationsEntity>
    ): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND ->
                    return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    val remoteKey: LocationsPageKey? = db.withTransaction {
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

            val response = service.getLocations(loadKey ?: 1)
            val resBody = response.body()
            val pageInfo = resBody?.pageInfo
            val locations = resBody?.results
            db.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    locationsDao.clearAll()
                    keyDao.clearAll()
                }
                locations?.forEach {
                    it.page = loadKey
                    keyDao.insertOrReplace(LocationsPageKey(it.id, pageInfo?.next))
                }
                locations?.let { locationsDao.insertAll(it) }
            }

            MediatorResult.Success(
                endOfPaginationReached = resBody?.pageInfo?.next == null
            )
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }
}