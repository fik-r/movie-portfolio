package com.example.myapplication.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.model.Movie
import com.example.usecase.SearchMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchMovieUseCase: SearchMovieUseCase
) : ViewModel() {

    fun searchUser(query: String): Flow<PagingData<Movie>> {
        return Pager(PagingConfig(pageSize = 10)) {
            SearchDataSource(searchMovieUseCase, query)
        }.flow.cachedIn(viewModelScope)
    }
}