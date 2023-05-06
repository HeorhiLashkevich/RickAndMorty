package com.example.rickandmorty

import android.app.Application
import com.example.rickandmorty.di.components.ApplicationComponent
import com.example.rickandmorty.di.components.DaggerApplicationComponent


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