package com.example.rickandmorty.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.rickandmorty.data.local.dao.*
import com.example.rickandmorty.data.model.RemoteKeys
import com.example.rickandmorty.data.local.dao.RemoteKeysDao
import com.example.rickandmorty.data.model.*


@Database(
    entities = [RemoteKeys::class, CharactersEntity::class, EpisodeEntity::class, LocationsEntity::class],
    version = 44
)
@TypeConverters(TypeConverter::class)
abstract class AppDataBase : RoomDatabase() {
    abstract fun getCharactersDao(): CharactersDao
    abstract fun getEpisodesDao(): EpisodesDao
    abstract fun getLocationsDao(): LocationsDao
//    abstract fun getCharactersPageKeyDao(): CharactersPageKeyDao
//    abstract fun getLocationsPageKeyDao(): LocationsPageKeyDao
//    abstract fun getEpisodesPageKeyDao(): EpisodesPageKeyDao
    abstract fun getRemoteKeyDao(): RemoteKeysDao




}