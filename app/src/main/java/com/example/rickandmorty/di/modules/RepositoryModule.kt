package com.example.rickandmorty.di.modules


import com.example.rickandmorty.data.repository.*
import dagger.Binds
import dagger.Module


@Module

abstract class RepositoryModule {

    @Binds
    abstract fun bindCharacterRepository(
        charRepoImpl: CharacterRepositoryImpl
    ): CharacterRepository

    @Binds
    abstract fun bindEpisodesRepository(
        episodesRepositoryImpl: EpisodesRepositoryImpl
    ): EpisodesRepository

    @Binds
    abstract fun bindLocationsRepository(
        locationsRepositoryImpl: LocationsRepositoryImpl
    ): LocationsRepository
}