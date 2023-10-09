package com.example.abstraction.datasource

import com.example.model.Movie
import com.example.model.SearchMovie
import kotlinx.coroutines.flow.Flow

interface MovieRemoteDataSource {
    fun fetchMovies(): Flow<List<Movie>>
    fun fetchMovie(id: String): Flow<Movie>
    fun searchMovie(query: String, page: Int): Flow<SearchMovie>
}