package com.example.rickandmorty.di.modules

import android.content.Context
import androidx.room.Room
import com.example.rickandmorty.data.local.AppDataBase
import com.example.rickandmorty.data.local.dao.CharactersDao
import com.example.rickandmorty.data.local.dao.EpisodeDao
import com.example.rickandmorty.data.local.dao.PageKeyDao
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
//

    }

    @Provides

    fun provideCharactersDao(database: AppDataBase): CharactersDao {
        return database.getCharactersDao()
    }
    @Provides

    fun provideEpisodesDao(database: AppDataBase): EpisodeDao {
        return database.getEpisodesDao()
    }


    @Provides

    fun providePageKeyDao(database: AppDataBase): PageKeyDao {
        return database.getPageKeyDao()
    }
}