package com.example.rickandmorty.repository

import com.example.rickandmorty.api.Characters
import com.example.rickandmorty.api.NetworkController
import retrofit2.Response

class CharactersRepository {

    suspend fun getCharacters(count: Int, page: Int): Response<Characters> {
       return NetworkController.getRickAndMortyApi().getCharacters(limit = count, page  = page)
    }

}