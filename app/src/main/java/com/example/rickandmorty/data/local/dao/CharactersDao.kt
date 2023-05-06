package com.example.rickandmorty.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rickandmorty.data.local.model.CharactersEntity

@Dao
interface CharactersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertAllCharacters(charactersEntity: List<CharactersEntity>)

    @Query("SELECT * FROM characters WHERE id = :id")
    fun charactersById(id: Int): PagingSource<Int, CharactersEntity>

    @Query("DELETE FROM characters WHERE id = :id")
    suspend fun deleteById(id: Int)



//    @Query("SELECT * FROM characters WHERE id LIKE :query")
//    fun pagingSource(query: Int): PagingSource<Int, CharactersEntity>
//
//    @Query("SELECT * FROM characters")
//     fun getAll(): List<CharactersEntity>
//
//    @Query("SELECT * FROM characters WHERE name LIKE :name")
//     fun findByName(name: String): List<CharactersEntity>



//    @Query("SELECT * FROM characters")
//    fun getAllCharacters(): ArrayList<CharactersEntity>
//
//    @Query("SELECT * FROM items WHERE characters LIKE :searchQuery")
//    fun searchDatabase(searchQuery: String): Flow<ArrayList<CharactersRoom>>

//    @Query("SELECT * FROM charactersRoom WHERE id = :id")
//    fun getCharacter(id: Int): LiveData<CharactersRoom>

//    @Query("SELECT * FROM CharactersRoom")
//    fun getAllCharactersRoom(): Flow<ArrayList<CharactersRoom>>


//    @Query("SELECT * FROM CharactersRoom")
//    suspend fun getCharacters(): PagingSource<Int, CharactersRoom>


}