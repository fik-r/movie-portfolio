package com.example.data.datasource


import com.example.abstraction.datasource.MovieLocalDataSource
import com.example.data.database.dao.MovieDao
import com.example.data.database.entity.mapToEntity
import com.example.data.database.entity.mapToModel
import com.example.model.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MovieLocalDataSourceImpl(
    private val movieDao: MovieDao
) : MovieLocalDataSource {
    override fun fetchMovies(): Flow<List<Movie>> = flow {
        val list = mutableListOf<Movie>()
        movieDao.getAllUser().forEach {
            list.add(it.mapToModel())
        }
        emit(list)
    }

    override fun findMovie(id: Int): Flow<Movie> = flow {
        emit(movieDao.findUser(id).mapToModel())
    }

    override fun insertMovie(movie: Movie): Flow<Unit> = flow {
        emit(movieDao.insertUser(movie.mapToEntity()))
    }

    override fun deleteMovie(movie: Movie): Flow<Unit> = flow {
        emit(movieDao.deleteUser(movie.mapToEntity()))
    }
}