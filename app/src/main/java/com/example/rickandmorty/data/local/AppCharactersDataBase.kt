package com.example.rickandmorty.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.rickandmorty.data.local.dao.CharactersDao
import com.example.rickandmorty.data.local.model.CharactersEntity


@Database(entities = [CharactersEntity::class], version = 5)
@TypeConverters(TypeConverter::class)
abstract class AppCharactersDataBase : RoomDatabase() {
    abstract fun gerCharactersDao(): CharactersDao

}