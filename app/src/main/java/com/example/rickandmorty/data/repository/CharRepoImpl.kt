package com.example.rickandmorty.data.repository

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.rickandmorty.data.local.AppDataBase
import com.example.rickandmorty.data.local.paging.datasource.CharactersDataSource
import com.example.rickandmorty.data.local.paging.remotemediator.CharactersRemoteMediator
import com.example.rickandmorty.data.model.CharactersEntity
import com.example.rickandmorty.data.remove.service.RickAndMortyApiService
import com.example.rickandmorty.utils.COUNT_ITEM_CHARACTERS
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharRepoImpl @Inject constructor(
    private val service: RickAndMortyApiService,
    private val dataBase: AppDataBase
) : CharRepo {



//    override suspend fun getSearchResultStream(query: String): Flow<PagingData<CharactersEntity>> {
//        return Pager(
//            config = PagingConfig(
//                pageSize = 20,
//                enablePlaceholders = false
//            ),
////            pagingSourceFactory = {CharactersDataSource (service, query) }
//            pagingSourceFactory = {CharactersDataSource (service) }
//        ).flow
//    }

//   override suspend fun getSearchResultStreamFromMediator(query: String): Flow<PagingData<CharactersEntity>> {
//        Log.d("CharRepo", "New query: $query")
//
//        // appending '%' so we can allow other characters to be before and after the query string
//        val dbQuery = "%${query.replace(' ', '%')}%"
//        val pagingSourceFactory = { dataBase.getCharactersDao().pagingSource(dbQuery) }
//
//        @OptIn(ExperimentalPagingApi::class)
//        return Pager(
//            config = PagingConfig(pageSize = 1, enablePlaceholders = false),
//            remoteMediator = CharactersRemoteMediator(
//                query,
//                service,
//                dataBase
//            ),
//            pagingSourceFactory = pagingSourceFactory
//        ).flow
//    }

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
        remoteMediator = CharactersRemoteMediator(  service, dataBase)
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
            remoteMediator = CharactersRemoteMediator(service, dataBase)
        ) {
            dataBase.getCharactersDao().pagingSource()
//        dataSource
//        db.gerCharactersDao().pagingSource()
        }.flow


}