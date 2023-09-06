package com.example.movie.ui.home.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.movie.data.model.Movie.MovieResponse
import com.example.movie.data.repository.Movie.MovieRepository
import com.example.movie.ui.base.BaseViewModel
import com.example.movie.util.constants.ResourceStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val userRepository: MovieRepository) :
    BaseViewModel() {
    var allPopularMoviesLiveData = MutableLiveData<MovieResponse>()

    init {
        getAllPopularMovies()
    }

    fun getAllPopularMovies() = viewModelScope.launch {
        userRepository.getAllPopularMovies()
            .asLiveData(viewModelScope.coroutineContext).observeForever {
                when (it.status) {
                    ResourceStatus.LOADING -> {
                        loading.postValue(true)
                    }

                    ResourceStatus.SUCCESS -> {
                        allPopularMoviesLiveData.postValue(it.data!!)
                        loading.postValue(false)
                    }

                    ResourceStatus.ERROR -> {

                        error.postValue(it.throwable!!)
                        loading.postValue(false)
                    }
                }
            }
    }
}