package com.example.rickandmorty.di


import com.example.rickandmorty.ui.characterdetails.CharactersDetailsFragment
import com.example.rickandmorty.ui.characters.CharactersFragment
import com.example.rickandmorty.ui.episodes.EpisodesFragment
import com.example.rickandmorty.ui.episodesdetails.EpisodeDetailsFragment
import com.example.rickandmorty.ui.locationdetails.LocationDetailsFragment
import com.example.rickandmorty.ui.locations.LocationsFragment
import dagger.Component
import javax.inject.Singleton

@Component(modules = [NetworkModule::class])
@Singleton
interface ApplicationComponent {

    fun inject(fragment: CharactersFragment)
    fun inject(fragment: EpisodesFragment)
    fun inject(fragment: LocationsFragment)
    fun inject(fragment: EpisodeDetailsFragment)
    fun inject(fragment: CharactersDetailsFragment)
    fun inject(fragment: LocationDetailsFragment)

}

