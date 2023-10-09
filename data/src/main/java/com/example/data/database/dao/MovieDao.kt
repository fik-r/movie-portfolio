package com.example.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.database.entity.MovieEntity

@Dao
interface MovieDao {

    @Query("Select * From movie")
    suspend fun getAllUser(): List<MovieEntity>

    @Query("Select * From movie where id = :id")
    suspend fun findUser(id: Int): MovieEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(movieEntity: MovieEntity)

    @Delete
    suspend fun deleteUser(movieEntity: MovieEntity)
}