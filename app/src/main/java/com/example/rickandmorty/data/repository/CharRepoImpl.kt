package com.example.rickandmorty.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.rickandmorty.data.local.AppDataBase
import com.example.rickandmorty.data.local.paging.remotemediator.CharactersRemoteMediator
import com.example.rickandmorty.data.model.CharactersEntity
import com.example.rickandmorty.data.remove.service.RickAndMortyApiService
import com.example.rickandmorty.utils.COUNT_ITEM_CHARACTERS
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharRepoImpl @Inject constructor(
    private val api: RickAndMortyApiService,
    private val dataBase: AppDataBase
) : CharRepo {

    @OptIn(ExperimentalPagingApi::class)
    override suspend fun searchByName(
        query: String,
        count: Int,
        page: Int
    ): Flow<PagingData<CharactersEntity>> = Pager(
        config = PagingConfig(
            pageSize = COUNT_ITEM_CHARACTERS,
            initialLoadSize = 40,
            prefetchDistance = 40
        ),
        remoteMediator = CharactersRemoteMediator(api, dataBase)
    ) {
        dataBase.getCharactersDao().pagingSource()
//        dataSource
    }.flow

    @OptIn(ExperimentalPagingApi::class)
    override suspend fun getCharacters(count: Int, page: Int): Flow<PagingData<CharactersEntity>> =
        Pager(
            config = PagingConfig(
                pageSize = COUNT_ITEM_CHARACTERS,
                initialLoadSize = 40,
                prefetchDistance = 40
            ),
            remoteMediator = CharactersRemoteMediator(api, dataBase)
        ) {
            dataBase.getCharactersDao().pagingSource()
//        dataSource
//        db.gerCharactersDao().pagingSource()
        }.flow


}