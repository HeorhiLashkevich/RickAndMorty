package com.example.rickandmorty.di.modules

import android.content.Context
import androidx.room.Room
import com.example.rickandmorty.data.local.AppDataBase
import com.example.rickandmorty.data.local.dao.*
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object DataBaseModule {
    @Singleton
    @Provides
    fun initDataBase(
        context: Context
    ): AppDataBase {
        return Room.databaseBuilder(
            context,
            AppDataBase::class.java, "databaseRickAndMorty"
        )
            .fallbackToDestructiveMigration()
            .build()

    }

    @Provides

    fun provideCharactersDao(database: AppDataBase): CharactersDao {
        return database.getCharactersDao()
    }

    @Provides

    fun provideEpisodesDao(database: AppDataBase): EpisodesDao {
        return database.getEpisodesDao()
    }

    @Provides
    fun provideLocationsDao(database: AppDataBase): LocationsDao {
        return database.getLocationsDao()
    }

    @Provides
    fun provideCharactersPageKeyDao(database: AppDataBase): CharactersPageKeyDao {
        return database.getCharactersPageKeyDao()
    }

    @Provides
    fun provideEpisodesPageKeyDao(database: AppDataBase): EpisodesPageKeyDao {
        return database.getEpisodesPageKeyDao()
    }

    @Provides
    fun provideLocationsPageKeyDao(database: AppDataBase): LocationsPageKeyDao {
        return database.getLocationsPageKeyDao()
    }
}

//    @Provides
//    fun provideRemoteKeyDao(database: AppDataBase): RemoteKeysDao {
//        return database.getRemoteKeyDao()
//    }
//}