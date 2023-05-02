package com.example.rickandmorty.ui.characters.charactersDao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rickandmorty.ui.characters.db.CharactersRoom
import kotlinx.coroutines.flow.Flow


@Dao
interface CharactersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCharacters(charactersRoom: ArrayList<CharactersRoom>)

//    @Query("SELECT * FROM characters")
//    fun getAllCharacters(): Flow<ArrayList<CharactersRoom>>
//
//    @Query("SELECT * FROM items WHERE characters LIKE :searchQuery")
//    fun searchDatabase(searchQuery: String): Flow<ArrayList<CharactersRoom>>

//    @Query("SELECT * FROM charactersRoom WHERE id = :id")
//    fun getCharacter(id: Int): LiveData<CharactersRoom>

//    @Query("SELECT * FROM CharactersRoom")
//    fun getAllCharactersRoom(): Flow<ArrayList<CharactersRoom>>


//    @Query("SELECT * FROM CharactersRoom")
//    suspend fun getCharacters(): PagingSource<Int, CharactersRoom>

//    @Query("DELETE FROM CharactersRoom")
//    suspend fun clearAllCharacters()


}