package com.example.usecase
import com.example.abstraction.repository.MovieRepository
import com.example.common.UseCase
import com.example.model.Movie
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class GetMovieUseCase(
    private val movieRepository: MovieRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : UseCase<Movie, GetMovieUseCase.Params>() {
    override fun build(params: Params?): Flow<Movie> {
        requireNotNull(params)
        return movieRepository.fetchMovie(params.id).flowOn(dispatcher)
    }

    data class Params(
        val id: String,
    )
}