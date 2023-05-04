package com.example.rickandmorty

import android.app.Application
import com.example.rickandmorty.di.ApplicationComponent
import com.example.rickandmorty.di.DaggerApplicationComponent

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        initAppComponent()
    }

    private fun initAppComponent() {
        appComponent = DaggerApplicationComponent.builder()
            .build()
    }

    companion object {
        lateinit var appComponent: ApplicationComponent
    }
}