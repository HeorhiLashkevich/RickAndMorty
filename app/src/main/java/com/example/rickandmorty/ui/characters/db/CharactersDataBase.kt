package com.example.rickandmorty.ui.characters.db

import android.content.Context
import androidx.room.Room
import com.example.rickandmorty.ui.characters.charactersDao.CharactersDao


object CharactersDataBase {

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

}