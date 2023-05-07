package com.example.rickandmorty.data.local.dao

import androidx.paging.PagingSource
import androidx.room.*
import com.example.rickandmorty.data.model.CharactersEntity

@Dao
interface CharactersDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertAll(characters: List<CharactersEntity>)

    @Query("SELECT * FROM characters")
    fun pagingSource(): PagingSource<Int, CharactersEntity>

    @Query("DELETE FROM characters")
     fun clearAll()
//    @Query("SELECT * FROM characters WHERE :name IS NULL OR name = :name")
//    fun getPagingSource(
//        name: String?
//    ): PagingSource<Int, CharactersEntity>
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//     suspend fun insertAllCharacters(charactersEntity: List<CharactersEntity>)
//    @Query(/* value = */ "DELETE FROM characters WHERE :name IS NULL OR name = :name")
//     fun clear(name: String?)
//    @Transaction
//    suspend fun refresh(name: String?, launches: Response<Characters>) {
//        clear(name)
//        save(launches)
//    }
//    suspend fun save(launch: Response<Characters>) {
//        save(launch)
//    }

    //    @Query(/* value = */ "SELECT * FROM characters WHERE name = :name")
//    fun charactersById(name: String): PagingSource<Int, CharactersEntity>

//    @Query(/* value = */ "DELETE FROM characters WHERE name = :name")
//    suspend fun deleteByName(name: String)



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