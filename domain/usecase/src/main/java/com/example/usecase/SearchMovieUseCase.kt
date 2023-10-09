package com.example.usecase

import com.example.abstraction.repository.MovieRepository
import com.example.common.UseCase
import com.example.model.SearchMovie
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class SearchMovieUseCase(
    private val movieRepository: MovieRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : UseCase<SearchMovie, SearchMovieUseCase.Params>() {
    override fun build(params: Params?): Flow<SearchMovie> {
        requireNotNull(params)
        return movieRepository.searchMovie(params.query, params.page).flowOn(dispatcher)
    }

    data class Params(
        val query: String,
        val page: Int
    )
}