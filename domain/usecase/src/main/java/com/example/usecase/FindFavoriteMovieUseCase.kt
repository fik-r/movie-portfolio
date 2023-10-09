package com.example.usecase
import com.example.abstraction.repository.MovieRepository
import com.example.common.UseCase
import com.example.model.Movie
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class FindFavoriteMovieUseCase(
    private val movieRepository: MovieRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : UseCase<Movie, Int>() {
    override fun build(params: Int?): Flow<Movie> {
        requireNotNull(params)
        return movieRepository.findMovie(params).flowOn(dispatcher)
    }
}