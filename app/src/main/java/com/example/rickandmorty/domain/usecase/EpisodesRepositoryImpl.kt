package com.example.rickandmorty.domain.usecase


import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.rickandmorty.data.local.AppDataBase
import com.example.rickandmorty.data.local.paging.remotemediator.EpisodeRemoteMediator
import com.example.rickandmorty.data.model.EpisodeEntity
import com.example.rickandmorty.data.api.RickAndMortyApi
import com.example.rickandmorty.domain.repository.EpisodesRepository
import com.example.rickandmorty.utils.COUNT_EPISODES_LOAD_SIZE
import com.example.rickandmorty.utils.COUNT_ITEM_EPISODES
import kotlinx.coroutines.flow.Flow

import javax.inject.Inject

class EpisodesRepositoryImpl @Inject constructor(
    private val service: RickAndMortyApi,
    private val dataBase: AppDataBase,
) : EpisodesRepository {
    @OptIn(ExperimentalPagingApi::class)
    override suspend fun searchByEpisodeName(
    ): Flow<PagingData<EpisodeEntity>> {
        return Pager(
            PagingConfig(
                pageSize = COUNT_ITEM_EPISODES,
                initialLoadSize = COUNT_EPISODES_LOAD_SIZE,


            ),
            remoteMediator = EpisodeRemoteMediator(service, dataBase),
            pagingSourceFactory = { dataBase.getEpisodesDao().pagingSource() }
        )
            .flow
    }

}
