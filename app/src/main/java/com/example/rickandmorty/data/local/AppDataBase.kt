package com.example.rickandmorty.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.rickandmorty.data.local.dao.*
import com.example.rickandmorty.data.model.CharactersEntity
import com.example.rickandmorty.data.model.EpisodeEntity
import com.example.rickandmorty.data.model.LocationsEntity
import com.example.rickandmorty.data.model.PageKey


@Database(
    entities = [CharactersEntity::class, PageKey::class, RemoteKeys::class, EpisodeEntity::class, LocationsEntity::class],
    version = 11
)
@TypeConverters(TypeConverter::class)
abstract class AppDataBase : RoomDatabase() {
    abstract fun getCharactersDao(): CharactersDao
    abstract fun getEpisodesDao(): EpisodesDao
    abstract fun getPageKeyDao(): PageKeyDao
    abstract fun getLocationsDao(): LocationsDao
    abstract fun getRemoteKeyDao(): RemoteKeysDao


}