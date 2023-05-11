package com.example.rickandmorty.present.characters


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.rickandmorty.data.local.AppDataBase
import com.example.rickandmorty.data.local.paging.datasource.CharactersDataSource
import com.example.rickandmorty.data.local.paging.remotemediator.CharactersRemoteMediator
import com.example.rickandmorty.data.remove.service.RickAndMortyApiService
import com.example.rickandmorty.data.repository.CharRepo
import com.example.rickandmorty.utils.COUNT_ITEM_CHARACTERS


class CharactersViewModel(
    private val dataSource: CharactersDataSource,
    private val repo: CharRepo,
    private val service: RickAndMortyApiService,
    private val db: AppDataBase,

    ) : ViewModel() {
    private val searchText = null

    @OptIn(ExperimentalPagingApi::class)
    val flow = Pager(
        config = PagingConfig(
            pageSize = COUNT_ITEM_CHARACTERS,
            initialLoadSize = 40,
            prefetchDistance = 40
        ),
        remoteMediator =
            CharactersRemoteMediator(service, db)

    ) {
        db.getCharactersDao().pagingSource()

//        dataSource
//        db.gerCharactersDao().pagingSource()
    }.flow.cachedIn(viewModelScope)//
    //    @OptIn(ExperimentalPagingApi::class)
//    val flow = Pager(
//        config = PagingConfig(
//            pageSize = COUNT_ITEM_CHARACTERS,
//            initialLoadSize = 40,
//            prefetchDistance = 40
//        ),
////        remoteMediator =
////            CharactersRemoteMediator(service, db)
//
//    ) {
////        db.getCharactersDao().pagingSource()
//
//        dataSource
////        db.gerCharactersDao().pagingSource()
//    }.flow.cachedIn(viewModelScope)


//

//    val flow =


}