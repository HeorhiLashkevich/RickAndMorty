//package com.example.rickandmorty.data.local.paging.datasource
//
//import androidx.paging.PagingSource
//import androidx.paging.PagingState
//import com.example.rickandmorty.data.model.CharactersEntity
//import com.example.rickandmorty.data.model.EpisodeEntity
//import com.example.rickandmorty.data.remove.service.RickAndMortyApiService
//import com.example.rickandmorty.data.repository.EpisodesRepository
//import retrofit2.HttpException
//import java.io.IOException
//import javax.inject.Inject
//
//
//class EpisodesDataSource @Inject constructor(
//    private val service: RickAndMortyApiService,
////    private val query: String
//) : PagingSource<Int, EpisodeEntity>() {
//
//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, EpisodeEntity> {
//        val position = params.key ?: 1
////        val apiQuery = query
//        return try {
//            val response = service.getEpisodes(position)
//            val repos = response.body()!!.results
//            val nextKey = if (repos.isEmpty()) {
//                null
//            } else {
//                position + (params.loadSize / 20)
//            }
//            LoadResult.Page(
//                data = repos,
//                prevKey = if (position == 1) null else position - 1,
//                nextKey = nextKey
//            )
//        } catch (exception: IOException) {
//            return LoadResult.Error(exception)
//        } catch (exception: HttpException) {
//            return LoadResult.Error(exception)
//        }
//    }
//
//    override fun getRefreshKey(state: PagingState<Int, EpisodeEntity>): Int? {
//        return state.anchorPosition?.let { anchorPosition ->
//            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
//                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
//        }
//    }
//
//}
//
////class EpisodesDataSource@Inject constructor(
////    private val repository: EpisodesRepository
////) : PagingSource<Int, EpisodeEntity>() {
////
////    override fun getRefreshKey(state: PagingState<Int, EpisodeEntity>): Int? {
////        return state.anchorPosition?.let { anchorPosition ->
////            val anchorPage = state.closestPageToPosition(anchorPosition)
////            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
////        }
////    }
////
////    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, EpisodeEntity> {
////        return try {
////            val key = params.key ?: 1
////            val response = repository.getEpisodes(params.loadSize)
////            val nextKey = key + 1
////
////            LoadResult.Page(
////                data = response.body()?.results as ArrayList<EpisodeEntity>,
////                prevKey = null,
////                nextKey = nextKey
////            )
////        } catch (e: java.lang.Exception) {
////            LoadResult.Error(e)
////        }
////    }
////}