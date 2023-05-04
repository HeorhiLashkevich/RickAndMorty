package com.example.rickandmorty.di.modules

import android.content.Context
import com.example.rickandmorty.ui.characters.charactersDao.CharactersDao
import com.example.rickandmorty.ui.characters.db.AppCharactersDataBase
import com.example.rickandmorty.ui.characters.db.CharactersDataBase
import com.example.rickandmorty.ui.characters.db.MyApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class DataBaseModule(private val application: MyApplication) {

    @Singleton
    @Provides
    fun getCharacterDao(appCharactersDataBase: AppCharactersDataBase):CharactersDao{
        return appCharactersDataBase.gerCharactersDao()
    }

//    @Singleton
//    @Provides
//    fun getRoomDb() {
//        return CharactersDataBase.initDataBase(provideAppContext())
//    }
//    @Singleton
//    @Provides
//    private fun provideAppContext():Context{
//        return  application.applicationContext
//    }
}