package com.example.rickandmorty.domain.repository

import androidx.paging.PagingData
import com.example.rickandmorty.data.model.CharactersEntity
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {


//    suspend   fun getCharacters(count: Int, page: Int): Flow<PagingData<CharactersEntity>>
//     fun searchByName(name: String? = null)
//            : Flow<PagingData<CharactersEntity>>
   fun getCharacters()
            : Flow<PagingData<CharactersEntity>>

}