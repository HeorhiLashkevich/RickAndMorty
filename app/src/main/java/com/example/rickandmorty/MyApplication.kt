package com.example.rickandmorty

import android.app.Application
import com.example.rickandmorty.di.modules.DataBaseModule

class MyApplication:Application() {
    init {
        DataBaseModule.initDataBase(applicationContext)
    }
}