package com.example.rickandmorty

import android.app.Application
import com.example.rickandmorty.di.components.ApplicationComponent
import com.example.rickandmorty.di.components.DaggerApplicationComponent
import com.example.rickandmorty.di.modules.ContextModule
import com.example.rickandmorty.di.modules.DataBaseModule


class App : Application() {
    override fun onCreate() {
        super.onCreate()
        initAppComponent()
    }

    private fun initAppComponent() {
        appComponent =  DaggerApplicationComponent.builder()
            .contextModule(ContextModule(this))
            .dataBaseModule(DataBaseModule)
            .build()
    }

    companion object {
        lateinit var appComponent: ApplicationComponent
    }


}