package com.example.rickandmorty.di.modules


import com.example.rickandmorty.domain.repository.CharacterRepository
import com.example.rickandmorty.domain.repository.EpisodesRepository
import com.example.rickandmorty.domain.repository.LocationsRepository
import com.example.rickandmorty.domain.usecase.CharacterRepositoryImpl
import com.example.rickandmorty.domain.usecase.EpisodesRepositoryImpl
import com.example.rickandmorty.domain.usecase.LocationsRepositoryImpl
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