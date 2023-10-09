package com.example.abstraction.datasource

import com.example.model.Movie
import kotlinx.coroutines.flow.Flow


interface MovieLocalDataSource {
    fun fetchMovies(): Flow<List<Movie>>
    fun findMovie(id: Int): Flow<Movie>
    fun insertMovie(movie: Movie): Flow<Unit>
    fun deleteMovie(movie: Movie): Flow<Unit>
}