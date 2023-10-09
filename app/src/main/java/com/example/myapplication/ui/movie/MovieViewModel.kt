package com.example.myapplication.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.model.Movie
import com.example.usecase.GetFavoriteMovieUseCase
import com.example.usecase.GetMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val getFavoriteMovieUseCase: GetFavoriteMovieUseCase
) : ViewModel() {

    private val _listMovies: MutableLiveData<List<Movie>> = MutableLiveData(listOf())
    val listMovies: LiveData<List<Movie>> = _listMovies
    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    fun fetchMovies() {
        _isLoading.postValue(true)
        getMoviesUseCase.execute()
            .onEach {
                _listMovies.postValue(it)
            }
            .catch {}
            .onCompletion {
                _isLoading.postValue(false)
            }
            .launchIn(viewModelScope)
    }


    fun findFavoriteUsers() {
        _isLoading.postValue(true)
        getFavoriteMovieUseCase.execute()
            .onEach {
                _listMovies.postValue(it)
            }
            .catch { }
            .onCompletion {
                _isLoading.postValue(false)
            }
            .launchIn(viewModelScope)
    }
}