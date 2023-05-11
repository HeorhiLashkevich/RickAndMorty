package com.example.rickandmorty.di.components


import android.app.Application
import android.content.Context
import com.example.rickandmorty.di.modules.ContextModule
import com.example.rickandmorty.di.modules.DataBaseModule
import com.example.rickandmorty.di.modules.NetworkModule
import com.example.rickandmorty.di.modules.RepositoryModule
import com.example.rickandmorty.present.characterdetails.CharactersDetailsFragment
import com.example.rickandmorty.present.characters.CharactersFragment
import com.example.rickandmorty.present.episodes.EpisodesFragment
import com.example.rickandmorty.present.episodesdetails.EpisodeDetailsFragment
import com.example.rickandmorty.present.locationdetails.LocationDetailsFragment
import com.example.rickandmorty.present.locations.LocationsFragment
import dagger.Component
import javax.inject.Singleton

@Component(modules = [NetworkModule::class, ContextModule::class, DataBaseModule::class, RepositoryModule::class])
@Singleton
interface ApplicationComponent {
    fun inject(fragment: CharactersFragment)
    fun inject(fragment: EpisodesFragment)
    fun inject(fragment: LocationsFragment)
    fun inject(fragment: EpisodeDetailsFragment)
    fun inject(fragment: CharactersDetailsFragment)
    fun inject(fragment: LocationDetailsFragment)
    fun build(): ApplicationComponent
    fun context(): Context
    fun applicationContext(): Application
}



