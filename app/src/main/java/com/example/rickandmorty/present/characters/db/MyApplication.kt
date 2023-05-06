package com.example.rickandmorty.present.characters.db

import android.app.Application

class MyApplication:Application() {
    init {
        CharactersDataBaseRepository.initDataBase(applicationContext)
    }
}