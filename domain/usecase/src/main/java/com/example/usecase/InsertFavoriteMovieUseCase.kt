package com.example.usecase
import com.example.abstraction.repository.MovieRepository
import com.example.common.UseCase
import com.example.model.Movie
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class InsertFavoriteMovieUseCase(
    private val movieRepository: MovieRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : UseCase<Unit, Movie>() {
    override fun build(params: Movie?): Flow<Unit> {
        requireNotNull(params)
        return movieRepository.insertMovie(params).flowOn(dispatcher)
    }
}