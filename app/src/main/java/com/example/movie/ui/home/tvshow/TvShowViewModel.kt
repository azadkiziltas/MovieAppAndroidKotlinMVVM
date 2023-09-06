package com.example.movie.ui.home.tvshow

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.movie.data.model.Movie.MovieResponse
import com.example.movie.data.model.TvShow.TvShowResponse
import com.example.movie.data.repository.Movie.MovieRepository
import com.example.movie.data.repository.TvShow.TvShowRepository
import com.example.movie.ui.base.BaseViewModel
import com.example.movie.util.constants.ResourceStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvShowViewModel @Inject constructor(private val userRepository: TvShowRepository) :
    BaseViewModel() {
    var allPopularTvShowLiveData = MutableLiveData<TvShowResponse>()

    init {
        getAllPopularTvShow()
    }

    fun getAllPopularTvShow() = viewModelScope.launch {
        userRepository.getAllPopularTvShow()
            .asLiveData(viewModelScope.coroutineContext).observeForever {
                when (it.status) {
                    ResourceStatus.LOADING -> {
                        loading.postValue(true)
                    }

                    ResourceStatus.SUCCESS -> {
                        allPopularTvShowLiveData.postValue(it.data!!)
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