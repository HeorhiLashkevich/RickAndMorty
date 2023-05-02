package com.example.rickandmorty.repository

import com.example.rickandmorty.api.Characters
import com.example.rickandmorty.api.RickAndMortyApi

import retrofit2.Response
import javax.inject.Inject

class CharactersRepository @Inject constructor(
    private val api: RickAndMortyApi
) {

    suspend fun getCharacters(count: Int, page: Int): Response<Characters> {
        return api.getCharacters(limit = count, page = page)
    }
//api.getCharacters(limit = count, page = page)

//    suspend fun insertCharacter(list: ArrayList<CharactersRoom>) {
//        CharactersDataBase.characterDao?.insertAllCharacters(list)
//    }


    //    private lateinit var charactersDao: CharactersDao
//
//    private val characters: ArrayList<CharactersRoom> = arrayListOf()
//
//    suspend fun insertItems(charactersRoom: ArrayList<CharactersRoom>) {
//        return charactersDao.insertAllCharacters(charactersRoom)
//    }
//
//    fun getAllCharacters(): Flow<ArrayList<CharactersRoom>> {
//        return charactersDao.getAllCharacters()
//    }
//
//    fun searchDatabase(searchQuery: String): Flow<ArrayList<CharactersRoom>> {
//        return charactersDao.searchDatabase(searchQuery)
//    }


//    suspend fun getSearchedCharactersByName(name: String): Response<ArrayList<CharactersResult>> {
//        return NetworkController.getRickAndMortyApi().getSearchedCharactersByName(name)
//    }
//
//    suspend fun getCharactersByStatus(
//        status: String
//    ): Response<ArrayList<CharactersResult>> {
//        return NetworkController.getRickAndMortyApi()
//            .getCharactersByStatus(status)
//    }
//
//    suspend fun getCharactersByGender(
//        gender: String
//    ): Response<ArrayList<CharactersResult>> {
//        return NetworkController.getRickAndMortyApi()
//            .getCharactersByGender(gender)
//    }
//
//    suspend fun getCharactersByStatusAndGender(
//        status: String,
//        gender: String,
//
//        ): Response<ArrayList<CharactersResult>> {
//        return NetworkController.getRickAndMortyApi()
//            .getCharactersByStatusAndGender(status = status, gender = gender)
//    }

}