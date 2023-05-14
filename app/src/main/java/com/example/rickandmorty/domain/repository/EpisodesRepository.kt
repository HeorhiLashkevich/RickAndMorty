package com.example.rickandmorty.domain.repository

import androidx.paging.PagingData
import com.example.rickandmorty.data.model.EpisodeEntity
import kotlinx.coroutines.flow.Flow

interface EpisodesRepository {
     fun searchByEpisodeName()
            : Flow<PagingData<EpisodeEntity>>

}