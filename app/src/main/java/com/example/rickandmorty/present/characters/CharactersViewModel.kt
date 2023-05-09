package com.example.rickandmorty.present.characters


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.rickandmorty.utils.COUNT_ITEM_CHARACTERS
import com.example.rickandmorty.api.CharactersResult
import com.example.rickandmorty.data.local.AppDataBase
import com.example.rickandmorty.data.local.paging.remotemediator.CharactersRemoteMediator
import com.example.rickandmorty.data.remove.service.RickAndMortyApi


class CharactersViewModel(
//    private val dataSource: CharactersDataSource,
    private val service: RickAndMortyApi,
    private val db: AppDataBase
) : ViewModel() {
    var characters = MutableLiveData<List<CharactersResult>>()

    @OptIn(ExperimentalPagingApi::class)
    val flow = Pager(
        config = PagingConfig(
            pageSize = COUNT_ITEM_CHARACTERS,
            prefetchDistance = 3
        ),
        remoteMediator = CharactersRemoteMediator(service, db)

    ) {
        db.getCharactersDao().pagingSource()

//        dataSource
//        db.gerCharactersDao().pagingSource()
    }.flow.cachedIn(viewModelScope)

}