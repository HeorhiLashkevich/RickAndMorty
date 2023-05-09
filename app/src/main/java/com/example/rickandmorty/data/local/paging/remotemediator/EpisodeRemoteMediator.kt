package com.example.rickandmorty.data.local.paging.remotemediator

import android.net.Uri
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.rickandmorty.data.local.AppDataBase
import com.example.rickandmorty.data.model.EpisodeEntity
import com.example.rickandmorty.data.model.PageKey
import com.example.rickandmorty.data.remove.service.RickAndMortyApi

@OptIn(ExperimentalPagingApi::class)
class EpisodeRemoteMediator(val service: RickAndMortyApi, val db: AppDataBase) :
    RemoteMediator<Int, EpisodeEntity>() {
    private val episodeDao = db.getEpisodesDao()
    private val keyDao = db.getPageKeyDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, EpisodeEntity>
    ): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND ->
                    return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    val remoteKey: PageKey? = db.withTransaction {
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

            val response = service.getEpisodes(loadKey ?: 1)
            val resBody = response.body()
            val pageInfo = resBody?.pageInfo
            val episodes = resBody?.results
            db.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    episodeDao.clearAll()
                    keyDao.clearAll()
                }
                episodes?.forEach {
                    it.page = loadKey
                    keyDao.insertOrReplace(PageKey(it.id, pageInfo?.next))
                }
                episodes?.let { episodeDao.insertAll(it) }
            }

            MediatorResult.Success(
                endOfPaginationReached = resBody?.pageInfo?.next == null
            )
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }

}