package com.example.madlevel5task2.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.madlevel5task2.model.Game

@Dao
interface GameDao {

    @Query("select * from gameTable order by `release`")
    fun getGames():LiveData<List<Game>>

    @Insert
    suspend fun insert(game: Game)

    @Insert
    suspend fun insertMany(games: List<Game>)

    @Delete
    suspend fun delete(game: Game)

    @Query("delete from gameTable")
    suspend fun deleteAll()
}