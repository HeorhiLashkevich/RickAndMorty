package com.example.rickandmorty.data.repository


import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.rickandmorty.data.local.AppDataBase
import com.example.rickandmorty.data.local.paging.remotemediator.CharactersRemoteMediator
import com.example.rickandmorty.data.local.paging.remotemediator.EpisodeRemoteMediator
import com.example.rickandmorty.data.model.CharactersEntity
import com.example.rickandmorty.data.model.EpisodeEntity
import com.example.rickandmorty.data.remove.service.RickAndMortyApiService
import com.example.rickandmorty.utils.COUNT_CHARACTERS_LOAD_SIZE
import com.example.rickandmorty.utils.COUNT_EPISODES_LOAD_SIZE
import com.example.rickandmorty.utils.COUNT_ITEM_CHARACTERS
import com.example.rickandmorty.utils.COUNT_ITEM_EPISODES
import kotlinx.coroutines.flow.Flow

import javax.inject.Inject

class EpisodesRepositoryImpl @Inject constructor(
    private val service: RickAndMortyApiService,
    private val dataBase: AppDataBase,
) : EpisodesRepository {
    @OptIn(ExperimentalPagingApi::class)
    override suspend fun searchByEpisodeName(
    ): Flow<PagingData<EpisodeEntity>> {
        return Pager(
            PagingConfig(
                pageSize = COUNT_ITEM_EPISODES,
                initialLoadSize = COUNT_EPISODES_LOAD_SIZE,
                prefetchDistance = COUNT_EPISODES_LOAD_SIZE

            ),
            remoteMediator = EpisodeRemoteMediator(service, dataBase),
            pagingSourceFactory = { dataBase.getEpisodesDao().pagingSource() }
        )
            .flow
    }

}
