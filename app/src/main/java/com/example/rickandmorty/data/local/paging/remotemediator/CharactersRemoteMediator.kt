package com.example.rickandmorty.data.remove.mediator

import android.net.Uri
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.rickandmorty.data.local.AppDataBase
import com.example.rickandmorty.data.model.CharactersEntity
import com.example.rickandmorty.data.model.PageKey
import com.example.rickandmorty.data.remove.service.RickAndMortyApi

@OptIn(ExperimentalPagingApi::class)
class CharactersRemoteMediator(val service: RickAndMortyApi, val db: AppDataBase) :
    RemoteMediator<Int, CharactersEntity>() {
    private val charactersDao = db.getCharactersDao()
    private val keyDao = db.getPageKeyDao()

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
                    val remoteKey: PageKey? = db.withTransaction {
                        if (lastItem?.id != null) {
                            keyDao.getNextPageKey(lastItem.id.toInt())
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
            val characters = resBody?.results
            db.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    charactersDao.clearAll()
                    keyDao.clearAll()
                }
                characters?.forEach {
                    it.page = loadKey
                    keyDao.insertOrReplace(PageKey(it.id, pageInfo?.next))
                }
                characters?.let { charactersDao.insertAll(it) }
            }

            MediatorResult.Success(
                endOfPaginationReached = resBody?.pageInfo?.next == null
            )
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }
}

