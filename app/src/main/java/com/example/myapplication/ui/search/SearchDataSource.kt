package com.example.myapplication.ui.search

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.model.Movie
import com.example.myapplication.BuildConfig
import com.example.usecase.SearchMovieUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.withContext
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class SearchDataSource(
    private val searchMovieUseCase: SearchMovieUseCase,
    private val query: String
) :
    PagingSource<Int, Movie>() {

    private suspend fun fetchData(
        query: String,
        pageKey: Int
    ): LoadResult<Int, Movie> = withContext(Dispatchers.IO) {
        suspendCoroutine { continuation ->
            searchMovieUseCase.execute(
                SearchMovieUseCase.Params(query, pageKey)
            ).onEach {
                val responseData = mutableListOf<Movie>()
                responseData.addAll(it.items)
                val prevKey = if (pageKey == 1) null else pageKey - 1
                continuation.resume(LoadResult.Page(responseData, prevKey, pageKey + 1))
            }.catch { error ->
                continuation.resume(LoadResult.Error(error))
            }.launchIn(this)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val currentLoadingPageKey = params.key ?: 1
            fetchData(
                query,
                currentLoadingPageKey
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return null
    }

}