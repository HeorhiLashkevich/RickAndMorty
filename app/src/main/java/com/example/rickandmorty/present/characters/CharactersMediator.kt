//package com.example.rickandmorty.ui.characters
//
//import androidx.paging.ExperimentalPagingApi
//import androidx.paging.LoadType
//import androidx.paging.PagingState
//import androidx.paging.RemoteMediator
//import com.bumptech.glide.load.HttpException
//import com.example.rickandmorty.data.remove.service.model.CharactersResult
//import com.example.rickandmorty.data.remove.service.RickAndMortyApi
//import com.example.rickandmorty.data.local.dao.CharactersDao
//import com.example.rickandmorty.ui.characters.db.CharactersDataBaseRepository
//import com.example.rickandmorty.data.local.model.CharactersEntity
//import java.io.IOException
//
//
//@OptIn(ExperimentalPagingApi::class)
//class CharactersMediator(
//    private val db: CharactersDataBaseRepository,
//    private val api: RickAndMortyApi,
//    private val subredditName: String
//) : RemoteMediator<Int, CharactersEntity>() {
//    private val characterDao: CharactersDao? = db.gerCharactersDao()
////    private val remoteKeyDao: SubredditRemoteKeyDao = db.remoteKeys()
//
//    override suspend fun initialize(): InitializeAction {
//
//        return InitializeAction.LAUNCH_INITIAL_REFRESH
//    }
//
//    override suspend fun load(
//        loadType: LoadType,
//        state: PagingState<Int, CharactersEntity>
//    ): MediatorResult {
//        return try {
//            val loadKey = when (loadType) {
//                LoadType.REFRESH -> null
//
//                LoadType.PREPEND ->
//                    return MediatorResult.Success(endOfPaginationReached = true)
//                LoadType.APPEND -> {
//                    val lastItem = state.lastItemOrNull()
//                    if (lastItem == null) {
//                        return MediatorResult.Success(
//                            endOfPaginationReached = true
//                        )
//                    }
//                    lastItem.id
//                }
//            }
//
//            val response = networkService.searchUsers(
//                query = query, after = loadKey
//            )
//
//            database.withTransaction {
//                if (loadType == LoadType.REFRESH) {
//                    userDao.deleteByQuery(query)
//                }
//
//                characterDao?.insertAllCharacters(charactersEntity = response.users)
//            }
//
//            MediatorResult.Success(
//                endOfPaginationReached = response.nextKey == null
//            )
//        } catch (e: IOException) {
//            MediatorResult.Error(e)
//        } catch (e: HttpException) {
//            MediatorResult.Error(e)
//        }
//    }
//}