package com.example.rickandmorty.data.repository


import com.example.rickandmorty.data.model.EpisodeEntity
import com.example.rickandmorty.data.model.PagedResponse
import com.example.rickandmorty.data.remove.service.RickAndMortyApi

import retrofit2.Response
import javax.inject.Inject

class EpisodesRepository @Inject constructor(
    private val api: RickAndMortyApi,
) {
    suspend fun getEpisodes(page: Int): Response<PagedResponse<EpisodeEntity>> {
        return api.getEpisodes(page = page)
    }
//    suspend fun getEpisodes(count: Int, page: Int): Response<PagedResponse<EpisodeEntity>> {
//        return api.getEpisodes(limit = count, page = page)
//    }

//    suspend fun insertEpisodes(list: List<EpisodeEntity>) {
//        dataBase.getEpisodesDao().insertAll(list)
//    }
//    suspend fun deleteEpisodes() {
//        dataBase.getEpisodesDao().(list)
//    }
//    suspend fun insertEpisodes(list: List<EpisodeEntity>) {
//        dataBase.getEpisodesDao().insertAll(list)
//    }
}
