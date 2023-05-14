package com.example.rickandmorty.present.episodes


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.example.rickandmorty.data.local.AppDataBase
import com.example.rickandmorty.data.model.EpisodeEntity
import com.example.rickandmorty.data.api.RickAndMortyApi
import com.example.rickandmorty.domain.repository.EpisodesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class EpisodesViewModel @Inject constructor(
    private val repo: EpisodesRepository,
    private val service: RickAndMortyApi,
    private val db: AppDataBase,
) : ViewModel() {

    suspend fun searchEpisodes(): Flow<PagingData<EpisodeEntity>> {
        return repo.searchByEpisodeName().cachedIn(viewModelScope)
    }

//    @OptIn(ExperimentalPagingApi::class)
//    val flow = Pager(
//        PagingConfig(
//            pageSize = COUNT_ITEM_EPISODES, initialLoadSize = COUNT_EPISODES_LOAD_SIZE,
//            prefetchDistance = COUNT_EPISODES_LOAD_SIZE
//
//        ),
//        remoteMediator = EpisodeRemoteMediator(service, db)
//    ) {
//        db.getEpisodesDao().pagingSource()
////        dataSource
//    }.flow.cachedIn(viewModelScope)


}









