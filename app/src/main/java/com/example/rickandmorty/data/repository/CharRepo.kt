package com.example.rickandmorty.data.repository

import androidx.paging.PagingData
import com.example.rickandmorty.data.model.CharactersEntity
import kotlinx.coroutines.flow.Flow

interface CharRepo {


    suspend   fun getCharacters(count: Int, page: Int): Flow<PagingData<CharactersEntity>>
    suspend fun searchByName(query: String, count: Int, page: Int)
            : Flow<PagingData<CharactersEntity>>

//    suspend fun getSearchResultStream(query: String): Flow<PagingData<CharactersEntity>>
//    suspend fun getSearchResultStreamFromMediator(query: String): Flow<PagingData<CharactersEntity>>

}