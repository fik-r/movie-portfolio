package com.example.abstraction.repository

import com.example.model.Movie
import com.example.model.SearchMovie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun fetchMovies(): Flow<List<Movie>>
    fun fetchMovie(id: String): Flow<Movie>
    fun searchMovie(query: String, page: Int): Flow<SearchMovie>
    fun fetchMovieFromDb(): Flow<List<Movie>>
    fun findMovie(id: Int): Flow<Movie>
    fun insertMovie(movie: Movie): Flow<Unit>
    fun deleteMovie(movie: Movie): Flow<Unit>
}