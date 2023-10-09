package com.example.usecase
import com.example.abstraction.repository.MovieRepository
import com.example.common.UseCase
import com.example.model.Movie
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class GetMoviesUseCase(
    private val movieRepository: MovieRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
): UseCase<List<Movie>, Unit>() {
    override fun build(params: Unit?): Flow<List<Movie>> {
        return movieRepository.fetchMovies().flowOn(dispatcher)
    }
}