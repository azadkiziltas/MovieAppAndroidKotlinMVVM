package com.example.movie.ui.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.movie.data.model.Movie.MovieDetailsResponse
import com.example.movie.data.model.Movie.MovieImagesResponse
import com.example.movie.data.repository.MovieDetails.MovieDetailsRepository
import com.example.movie.data.repository.MovieImages.MovieImagesRepository
import com.example.movie.ui.base.BaseViewModel
import com.example.movie.util.constants.ResourceStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailsViewModel @Inject constructor(private val detailsRepository: MovieDetailsRepository,private val imagesRepository: MovieImagesRepository)  : BaseViewModel() {

    var movieDetailsLiveData = MutableLiveData<MovieDetailsResponse>()
    var movieImagesLiveData = MutableLiveData<MovieImagesResponse>()


    fun getMovieDetails(movieId:String) = viewModelScope.launch {
        detailsRepository.getMovieDetails(movieId)
            .asLiveData(viewModelScope.coroutineContext).observeForever {
                when (it.status) {
                    ResourceStatus.LOADING -> {
                        loading.postValue(true)
                    }

                    ResourceStatus.SUCCESS -> {
                        movieDetailsLiveData.postValue(it.data!!)
                        loading.postValue(false)
                    }

                    ResourceStatus.ERROR -> {

                        error.postValue(it.throwable!!)
                        loading.postValue(false)
                    }
                }
            }
    }

    fun getMovieImages(movieId:String) = viewModelScope.launch {
        imagesRepository.getMovieImages(movieId)
            .asLiveData(viewModelScope.coroutineContext).observeForever {
                when (it.status) {
                    ResourceStatus.LOADING -> {
                        loading.postValue(true)
                    }

                    ResourceStatus.SUCCESS -> {
                        movieImagesLiveData.postValue(it.data!!)
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