package com.example.rickandmorty.data.repository

import com.example.rickandmorty.api.Characters
import com.example.rickandmorty.api.CharactersResult
import com.example.rickandmorty.data.model.CharactersEntity
import com.example.rickandmorty.data.model.EpisodeEntity
import com.example.rickandmorty.data.model.PagedResponse
import com.example.rickandmorty.data.remove.service.RickAndMortyApi
import com.example.rickandmorty.di.modules.DataBaseModule


import retrofit2.Response
import javax.inject.Inject

class CharactersRepository @Inject constructor(
    private val api: RickAndMortyApi,
//   private val dataBaseModule: DataBaseModule
//    private val charactersDao: CharactersDao
) {

    suspend fun getCharacters(count: Int, page: Int): Response<PagedResponse<CharactersEntity>> {
        return api.getCharacters(limit = count, page = page)
    }

    suspend fun insertCharacter(list: List<CharactersEntity>) {
//        dataBaseModule.characterDao?.insertAll(list)
//        DataBaseModule.characterDao?.insertAll(list) as List<*>
    }

//    suspend fun getAllCharacters(): List<CharactersEntity> {
//        return CharactersDataBaseRepisitory.getAll()
//    }

    suspend fun getSearchedCharactersByName(name: String): Response<ArrayList<CharactersResult>> {
        return api.getSearchedCharactersByName(name)
    }



//    suspend fun searchByName(name: String) {
//        CharactersDataBaseRepository.characterDao?.findByName(name)
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