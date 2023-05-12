package com.example.rickandmorty.data.repository

import androidx.paging.PagingData
import com.example.rickandmorty.data.model.CharactersEntity
import com.example.rickandmorty.data.remove.service.model.CharactersResult
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {


//    suspend   fun getCharacters(count: Int, page: Int): Flow<PagingData<CharactersEntity>>
    suspend fun searchByName(name: String? = null)
            : Flow<PagingData<CharactersResult>>
  suspend fun getCharacters()
            : Flow<PagingData<CharactersEntity>>

}