package com.example.rickandmorty.ui.characters.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.rickandmorty.ui.characters.charactersDao.CharactersDao


@Database(entities = [CharactersRoom::class], version = 4)
@TypeConverters(TypeConverter::class)
abstract class AppCharactersDataBase : RoomDatabase() {

    abstract fun gerCharactersDao(): CharactersDao

}