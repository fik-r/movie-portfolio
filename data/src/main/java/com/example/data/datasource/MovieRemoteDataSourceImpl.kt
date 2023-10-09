package com.example.data.datasource

import com.example.abstraction.datasource.MovieRemoteDataSource
import com.example.data.api.response.toModel
import com.example.data.api.service.MovieService
import com.example.model.Movie
import com.example.model.SearchMovie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MovieRemoteDataSourceImpl(
    private val movieService: MovieService
) : MovieRemoteDataSource {
    override fun fetchMovies(): Flow<List<Movie>> = flow {
        emit(movieService.getMovies().results.toModel())
    }

    override fun fetchMovie(id: String): Flow<Movie> = flow {
        emit(movieService.getMovie(id).toModel())
    }

    override fun searchMovie(query: String, page: Int): Flow<SearchMovie> = flow {
        emit(movieService.searchMovies(query, page).toModel())
    }
}