package com.example.myapplication.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.model.Movie
import com.example.usecase.DeleteFavoriteMovieUseCase
import com.example.usecase.FindFavoriteMovieUseCase
import com.example.usecase.GetMovieUseCase
import com.example.usecase.InsertFavoriteMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getMovieUseCase: GetMovieUseCase,
    private val insertFavoriteMovieUseCase: InsertFavoriteMovieUseCase,
    private val deleteFavoriteMovieUseCase: DeleteFavoriteMovieUseCase,
    private val findFavoriteMovieUseCase: FindFavoriteMovieUseCase
) : ViewModel() {

    private val _user: MutableLiveData<Movie> = MutableLiveData()
    val user: MutableLiveData<Movie> = _user
    private val _toggleFavorite: MutableLiveData<Boolean> = MutableLiveData()
    val toggleFavorite: MutableLiveData<Boolean> = _toggleFavorite

    fun fetchMovies(id: String) {
        getMovieUseCase.execute(GetMovieUseCase.Params(id))
            .onEach {
                _user.postValue(it)
            }
            .catch { }
            .launchIn(viewModelScope)
    }

    fun findFavoriteMovies(id: Int) {
        findFavoriteMovieUseCase.execute(id)
            .onEach {
                if (it.id.isNotEmpty())
                    _toggleFavorite.postValue(true) else
                    _toggleFavorite.postValue(false)
            }
            .catch { }
            .launchIn(viewModelScope)
    }

    fun insertFavorite(movie: Movie) {
        insertFavoriteMovieUseCase.execute(movie)
            .onEach {
                _toggleFavorite.postValue(true)
            }
            .catch { }
            .launchIn(viewModelScope)
    }

    fun deleteFavorite(movie: Movie) {
        deleteFavoriteMovieUseCase.execute(movie)
            .onEach {
                _toggleFavorite.postValue(false)
            }
            .catch { }
            .launchIn(viewModelScope)
    }
}