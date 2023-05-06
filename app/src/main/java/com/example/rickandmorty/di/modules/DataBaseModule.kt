package com.example.rickandmorty.di.modules

import com.example.rickandmorty.data.local.dao.CharactersDao
import com.example.rickandmorty.data.local.AppCharactersDataBase
import com.example.rickandmorty.present.characters.db.MyApplication
import javax.inject.Singleton



class DataBaseModule(private val application: MyApplication) {

    @Singleton
//    @Provides
    fun getCharacterDao(appCharactersDataBase: AppCharactersDataBase): CharactersDao {
        return appCharactersDataBase.gerCharactersDao()
    }

//    @Singleton
//    @Provides
//    fun getRoomDb() {
//        return CharactersDataBase.initDataBase(provideAppContext())
//    }
//    @Singleton
//    @Provides
//     fun provideAppContext():Context{
//        return  application.applicationContext
//    }
}