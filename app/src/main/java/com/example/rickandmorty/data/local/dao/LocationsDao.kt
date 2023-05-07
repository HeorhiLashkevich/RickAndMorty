package com.example.rickandmorty.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rickandmorty.data.model.LocationsEntity


@Dao
interface LocationsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(episodes: List<LocationsEntity>)

    @Query("SELECT * FROM locations")
    fun pagingSource(): PagingSource<Int, LocationsEntity>

    @Query("DELETE FROM locations")
    suspend fun clearAll()
}
