package com.example.rickandmorty.di

import com.example.rickandmorty.ui.characters.CharactersFragment
import com.example.rickandmorty.ui.episodes.EpisodesFragment
import com.example.rickandmorty.ui.locations.LocationsFragment
import dagger.Component
import javax.inject.Singleton

@Component(modules = [NetworkController::class])
@Singleton
interface ApplicationComponent {

    fun inject(fragment: CharactersFragment)
    fun inject(fragment: EpisodesFragment)
    fun inject(fragment: LocationsFragment)
}