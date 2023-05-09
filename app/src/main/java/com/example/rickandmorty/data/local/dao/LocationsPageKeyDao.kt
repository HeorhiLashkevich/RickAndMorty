package com.example.rickandmorty.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rickandmorty.data.model.LocationsPageKey

@Dao
interface LocationsPageKeyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrReplace(locationsPageKey: LocationsPageKey)

    @Query("SELECT * FROM locationsPageKey WHERE id LIKE :id")
    fun getNextPageKey(id: Int): LocationsPageKey?

    @Query("DELETE FROM locationsPageKey")
    suspend fun clearAll()
}