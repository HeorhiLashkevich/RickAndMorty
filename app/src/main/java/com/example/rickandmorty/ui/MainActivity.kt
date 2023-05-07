package com.example.rickandmorty.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.rickandmort.R
import com.example.rickandmorty.present.characters.CharactersFragment
import com.example.rickandmorty.present.episodes.EpisodesFragment
import com.example.rickandmorty.present.locations.LocationsFragment

import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {
    lateinit var bottomNav: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        CharactersDataBaseModule.initDataBase(this)
        loadFragment(CharactersFragment())
        bottomNav = findViewById<BottomNavigationView>(R.id.bottomNav)
        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.nav_characters -> {
                    loadFragment(CharactersFragment())
                    true
                }
                R.id.nav_episodes -> {
                    loadFragment(EpisodesFragment())
                    true
                }
                R.id.nav_locations -> {
                    loadFragment(LocationsFragment())
                    true
                }
                else -> {
                    false
                }
            }
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .add(R.id.container, fragment)
            .addToBackStack("")
            .commit()
    }
}