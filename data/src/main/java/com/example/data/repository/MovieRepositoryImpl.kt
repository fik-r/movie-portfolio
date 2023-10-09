package com.example.data.repository

import com.example.abstraction.datasource.MovieLocalDataSource
import com.example.abstraction.datasource.MovieRemoteDataSource
import com.example.abstraction.repository.MovieRepository
import com.example.model.Movie
import com.example.model.SearchMovie
import kotlinx.coroutines.flow.Flow

class MovieRepositoryImpl(
    private val remote: MovieRemoteDataSource,
    private val local: MovieLocalDataSource
) : MovieRepository {
    override fun fetchMovies(): Flow<List<Movie>> {
        return remote.fetchMovies()
    }

    override fun fetchMovie(id: String): Flow<Movie> {
        return remote.fetchMovie(id)
    }

    override fun searchMovie(query: String, page: Int): Flow<SearchMovie> {
        return remote.searchMovie(query, page)
    }

    override fun fetchMovieFromDb(): Flow<List<Movie>> {
        return local.fetchMovies()
    }

    override fun findMovie(id: Int): Flow<Movie> {
        return local.findMovie(id)
    }

    override fun insertMovie(movie: Movie): Flow<Unit> {
        return local.insertMovie(movie)
    }

    override fun deleteMovie(movie: Movie): Flow<Unit> {
        return local.deleteMovie(movie)
    }
}