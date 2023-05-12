package com.example.rickandmorty.data.repository

import androidx.paging.PagingData
import com.example.rickandmorty.data.model.EpisodeEntity
import kotlinx.coroutines.flow.Flow

interface EpisodesRepository {
    suspend fun searchByEpisodeName()
            : Flow<PagingData<EpisodeEntity>>

}