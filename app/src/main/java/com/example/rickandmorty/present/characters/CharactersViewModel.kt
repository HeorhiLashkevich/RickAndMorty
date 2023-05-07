package com.example.rickandmorty.present.characters


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.rickandmorty.COUNT_ITEM_CHARACTERS
import com.example.rickandmorty.api.CharactersResult
import com.example.rickandmorty.data.local.AppDataBase
import com.example.rickandmorty.data.local.paging.datasource.CharactersDataSource
import com.example.rickandmorty.data.remove.mediator.CharactersRemoteMediator
import com.example.rickandmorty.data.remove.service.RickAndMortyApi


class CharactersViewModel(
//    private val dataSource: CharactersDataSource,
    service: RickAndMortyApi,
    db: AppDataBase
) : ViewModel() {
    var characters = MutableLiveData<List<CharactersResult>>()

    @OptIn(ExperimentalPagingApi::class)
    val flow = Pager(
        config = PagingConfig(
            pageSize = COUNT_ITEM_CHARACTERS,
            prefetchDistance = 20
        ),
        remoteMediator = CharactersRemoteMediator(service, db)

    ) {
                db.getCharactersDao().pagingSource() 

//        dataSource
//        db.gerCharactersDao().pagingSource()
    }.flow.cachedIn(viewModelScope)

}