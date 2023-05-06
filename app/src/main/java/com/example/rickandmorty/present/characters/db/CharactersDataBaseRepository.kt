package com.example.rickandmorty.present.characters.db

import android.content.Context
import androidx.room.Room
import com.example.rickandmorty.data.remove.service.RickAndMortyApi
import com.example.rickandmorty.data.local.AppCharactersDataBase
import com.example.rickandmorty.data.local.dao.CharactersDao


object CharactersDataBaseRepository {
    private lateinit var api: RickAndMortyApi
    private var db: AppCharactersDataBase? = null
    var characterDao: CharactersDao? = null


    fun initDataBase(context: Context) {
        val db = Room.databaseBuilder(
            context,
            AppCharactersDataBase::class.java, "database-character-name"
        )
            .fallbackToDestructiveMigration()
            .build()
        characterDao = db.gerCharactersDao()

    }


//    fun provideYourDao(db: CharactersDataBase) = db.characterDao
}