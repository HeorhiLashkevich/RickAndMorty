package com.example.rickandmorty.present.episodes


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.example.rickandmorty.data.local.AppDataBase
import com.example.rickandmorty.data.model.EpisodeEntity
import com.example.rickandmorty.data.remove.service.RickAndMortyApiService
import com.example.rickandmorty.data.repository.EpisodesRepository
import com.example.rickandmorty.utils.COUNT_EPISODES_LOAD_SIZE
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class EpisodesViewModel @Inject constructor(
    private val repo: EpisodesRepository,
    private val service: RickAndMortyApiService,
    private val db: AppDataBase,
//    private val dataSource: EpisodesDataSource
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









