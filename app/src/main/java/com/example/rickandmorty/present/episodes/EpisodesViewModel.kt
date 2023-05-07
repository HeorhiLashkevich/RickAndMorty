package com.example.rickandmorty.present.episodes


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.rickandmorty.COUNT_ITEM_EPISODES
import com.example.rickandmorty.data.local.AppDataBase
import com.example.rickandmorty.data.local.paging.datasource.EpisodesDataSource
import com.example.rickandmorty.data.local.paging.remotemediator.EpisodeRemoteMediator
import com.example.rickandmorty.data.remove.service.RickAndMortyApi
import javax.inject.Inject


class EpisodesViewModel @Inject constructor(
     service: RickAndMortyApi,
    db: AppDataBase
//    dataSource: EpisodesDataSource
) : ViewModel() {

    @OptIn(ExperimentalPagingApi::class)
    val flow = Pager(
        PagingConfig(pageSize = COUNT_ITEM_EPISODES, initialLoadSize = 20),
        remoteMediator = EpisodeRemoteMediator(service, db)
    ) {
        db.getEpisodesDao().pagingSource()
//        dataSource
    }.flow.cachedIn(viewModelScope)


}









