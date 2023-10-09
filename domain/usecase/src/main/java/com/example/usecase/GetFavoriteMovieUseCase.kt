package com.example.usecase
import com.example.abstraction.repository.MovieRepository
import com.example.common.UseCase
import com.example.model.Movie
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class GetFavoriteMovieUseCase(
    private val movieRepository: MovieRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
): UseCase<List<Movie>, Void>() {
    override fun build(params: Void?): Flow<List<Movie>> {
        return movieRepository.fetchMovieFromDb().flowOn(dispatcher)
    }
}