package com.example.rickandmorty.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.rickandmorty.data.local.dao.CharactersDao
import com.example.rickandmorty.data.local.dao.EpisodeDao
import com.example.rickandmorty.data.local.dao.PageKeyDao
import com.example.rickandmorty.data.model.CharactersEntity
import com.example.rickandmorty.data.model.EpisodeEntity
import com.example.rickandmorty.data.model.PageKey



@Database(entities = [CharactersEntity::class, PageKey::class, EpisodeEntity::class], version = 6)
@TypeConverters(TypeConverter::class)
abstract class AppDataBase : RoomDatabase() {
    abstract fun getCharactersDao(): CharactersDao
    abstract fun getEpisodesDao(): EpisodeDao
    abstract fun getPageKeyDao(): PageKeyDao
    companion object {
        @Volatile
        private var instance: AppDataBase? = null

        fun getDatabase( context: Context): AppDataBase =
            instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also {
                    instance = it
                }
            }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, AppDataBase::class.java, "databaseRickAndMorty")
                .fallbackToDestructiveMigration()
                .build()
    }

}