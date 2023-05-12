package com.example.rickandmorty.present.characters


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.example.rickandmorty.data.local.AppDataBase
import com.example.rickandmorty.data.local.paging.remotemediator.CharactersRemoteMediator
import com.example.rickandmorty.data.model.CharactersEntity
import com.example.rickandmorty.data.remove.service.RickAndMortyApiService
import com.example.rickandmorty.data.remove.service.model.CharactersResult
import com.example.rickandmorty.data.repository.CharacterRepository
import com.example.rickandmorty.utils.COUNT_CHARACTERS_LOAD_SIZE
import com.example.rickandmorty.utils.COUNT_ITEM_CHARACTERS
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class CharactersViewModel(
//    private val dataSource: CharactersDataSource,
    private val repo: CharacterRepository,
    private val service: RickAndMortyApiService,
    private val db: AppDataBase,
) : ViewModel() {
    var searchText = "Morty"




    suspend fun getCharactersByName(): Flow<PagingData<CharactersResult>> {
        return repo.searchByName(searchText).cachedIn(viewModelScope)
    }
//    suspend fun getCharacters(): Flow<PagingData<CharactersEntity>> {
//        return repo.getCharacters().cachedIn(viewModelScope)
//    }


//    @OptIn(ExperimentalPagingApi::class)
//    val flow Pager(
//            config = PagingConfig(
//                pageSize = COUNT_ITEM_CHARACTERS,
//                initialLoadSize = COUNT_CHARACTERS_LOAD_SIZE,
//                prefetchDistance = COUNT_CHARACTERS_LOAD_SIZE
//            ),
//            remoteMediator = CharactersRemoteMediator(service,dataBase, name = name),
//            db.getCharactersDao().pagingSource(searchText)
//        )
//            .flow
//            .map { it as PagingData<CharactersResult> }
//    }

//    @OptIn(ExperimentalPagingApi::class)
//    val flow = Pager(
//        config = PagingConfig(
//            pageSize = COUNT_ITEM_CHARACTERS,
//            initialLoadSize = COUNT_ITEM_CHARACTERS,
//            prefetchDistance = COUNT_ITEM_CHARACTERS
//        ),
//        remoteMediator =
//        CharactersRemoteMediator(service, db, searchText)
//
//    ) {
//        db.getCharactersDao().pagingSource(searchText)
//    }.flow.cachedIn(viewModelScope)//


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