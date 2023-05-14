package com.example.rickandmorty.domain.usecase

import androidx.paging.*
import com.example.rickandmorty.data.local.AppDataBase
import com.example.rickandmorty.data.local.paging.remotemediator.CharactersRemoteMediator
import com.example.rickandmorty.data.model.CharactersEntity
import com.example.rickandmorty.data.api.RickAndMortyApi
import com.example.rickandmorty.domain.repository.CharacterRepository
import com.example.rickandmorty.utils.COUNT_ITEM_CHARACTERS
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val service: RickAndMortyApi,
    private val dataBase: AppDataBase,

    ) : CharacterRepository {

//    @OptIn(ExperimentalPagingApi::class)
//    override  fun searchByName(name: String?): Flow<PagingData<CharactersEntity>> {
//        return Pager(
//            config = PagingConfig(
//                pageSize = COUNT_ITEM_CHARACTERS,
//                initialLoadSize = COUNT_CHARACTERS_LOAD_SIZE,
//            ),
//            remoteMediator = CharactersRemoteMediator(service,dataBase, name = name),
//            pagingSourceFactory = { dataBase.getCharactersDao().pagingSource(name) }
//        )
//            .flow
////            .map { it as PagingData<CharactersResult> }
//    }
    @OptIn(ExperimentalPagingApi::class)
    override   fun getCharacters(): Flow<PagingData<CharactersEntity>> {
        return Pager(
            config = PagingConfig(
                pageSize = COUNT_ITEM_CHARACTERS,

            ),
            remoteMediator = CharactersRemoteMediator(service,dataBase,),
            pagingSourceFactory = { dataBase.getCharactersDao().pagingSource() }
        )
            .flow
    }






//    @OptIn(ExperimentalPagingApi::class)
//    override suspend fun getCharacters(count: Int, page: Int): Flow<PagingData<CharactersEntity>> =
//        Pager(
//            config = PagingConfig(
//                pageSize = COUNT_ITEM_CHARACTERS,
//                initialLoadSize = COUNT_CHARACTERS_LOAD_SIZE,
//                prefetchDistance = COUNT_CHARACTERS_LOAD_SIZE
//            ),
//            remoteMediator = CharactersRemoteMediator(service, dataBase)
//        ) {
//            dataBase.getCharactersDao().pagingSource()
////        dataSource
////        db.gerCharactersDao().pagingSource()
//        }.flow


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


}