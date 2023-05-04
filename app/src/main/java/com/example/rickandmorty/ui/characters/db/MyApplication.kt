package com.example.rickandmorty.ui.characters.db

import android.app.Application

class MyApplication:Application() {
    init {
        CharactersDataBase.initDataBase(applicationContext)
    }
}