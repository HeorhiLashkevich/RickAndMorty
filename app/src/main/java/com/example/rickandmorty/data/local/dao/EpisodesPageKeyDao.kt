package com.example.rickandmorty.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rickandmorty.data.model.EpisodesPageKey

@Dao
interface EpisodesPageKeyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrReplace(episodesPageKey: EpisodesPageKey)

    @Query("SELECT * FROM episodesPageKey WHERE id LIKE :id")
    fun getNextPageKey(id: Int): EpisodesPageKey?

    @Query("DELETE FROM episodesPageKey")
    suspend fun clearAll()
}
