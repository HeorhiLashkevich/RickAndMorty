//package com.example.rickandmorty.data.local.paging.datasource
//
//import androidx.paging.PagingSource
//import androidx.paging.PagingState
//import com.example.rickandmorty.data.model.LocationsEntity
//import com.example.rickandmorty.data.api.RickAndMortyApiService
//import retrofit2.HttpException
//import java.io.IOException
//import javax.inject.Inject
//
//class LocationsDataStore @Inject constructor(
//    private val service: RickAndMortyApiService,
//) : PagingSource<Int, LocationsEntity>() {
//
//
//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, LocationsEntity> {
//        val position = params.key ?: 1
////        val apiQuery = query
//        return try {
////            val response = service.searchByName(apiQuery, position, params.loadSize)
//            val response =  service.getLocations (position)
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
//    override fun getRefreshKey(state: PagingState<Int, LocationsEntity>): Int? {
//        return state.anchorPosition?.let { anchorPosition ->
//            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
//                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
//        }
//    }
//
//}
//
