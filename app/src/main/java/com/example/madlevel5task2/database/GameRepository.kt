package com.example.madlevel5task2.database

import android.content.Context
import com.example.madlevel5task2.model.Game

class GameRepository (context: Context){
    private var gameDao: GameDao

    init {
        val database = GameRoomDatabase.getDatabase(context)
        gameDao = database!!.gameDoa()
    }
    fun gameHistory(){
        gameDao.getGames()
    }

    suspend fun insertGame(game: Game){
        return gameDao.insert(game)
    }

    suspend fun insertMany(games: List<Game>){
        return gameDao.insertMany(games)
    }

    suspend fun deleteHistory(){
        return gameDao.deleteAll()
    }

    fun getGames() = gameDao.getGames()

    suspend fun delete(game: Game) = gameDao.delete(game)
}