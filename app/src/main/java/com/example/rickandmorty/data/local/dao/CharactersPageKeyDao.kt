package com.example.rickandmorty.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rickandmorty.data.model.CharactersPageKey

@Dao
interface CharactersPageKeyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrReplace(charactersPageKey: CharactersPageKey)

    @Query("SELECT * FROM charactersPageKey WHERE id LIKE :id")
    fun getNextPageKey(id: Int): CharactersPageKey?

    @Query("DELETE FROM charactersPageKey")
    suspend fun clearAll()
}