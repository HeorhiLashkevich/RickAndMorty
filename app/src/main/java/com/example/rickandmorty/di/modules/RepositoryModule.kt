package com.example.rickandmorty.di.modules


import com.example.rickandmorty.data.repository.CharRepo
import com.example.rickandmorty.data.repository.CharRepoImpl
import dagger.Binds
import dagger.Module


@Module

abstract class RepositoryModule {

    @Binds
    abstract fun bindCharacterRepository(
        charRepoImpl: CharRepoImpl
    ): CharRepo
}