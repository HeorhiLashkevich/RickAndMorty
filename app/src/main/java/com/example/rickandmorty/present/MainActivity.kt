package com.example.rickandmorty.present

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.rickandmort.R
import com.example.rickandmorty.present.characters.CharactersFragment
import com.example.rickandmorty.present.episodes.EpisodesFragment
import com.example.rickandmorty.present.locations.LocationsFragment
import com.example.rickandmorty.utils.CHARACTERS_TAG
import com.example.rickandmorty.utils.EPISODES_TAG
import com.example.rickandmorty.utils.LOCATIONS_TAG

import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {
    lateinit var bottomNav: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadFragment(CharactersFragment(),CHARACTERS_TAG )
        bottomNav = findViewById<BottomNavigationView>(R.id.bottomNav)
        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.nav_characters -> {
                    loadFragment(CharactersFragment(),CHARACTERS_TAG)
                    true
                }
                R.id.nav_episodes -> {
                    loadFragment(EpisodesFragment(), EPISODES_TAG)
                    true
                }
                R.id.nav_locations -> {
                    loadFragment(LocationsFragment(),LOCATIONS_TAG)
                    true
                }
                else -> {
                    false
                }
            }
        }
    }

    private fun loadFragment(fragment: Fragment, backStack: String) {
        supportFragmentManager.beginTransaction()
            .add(R.id.container, fragment)
            .addToBackStack(backStack)
            .commit()
    }
}