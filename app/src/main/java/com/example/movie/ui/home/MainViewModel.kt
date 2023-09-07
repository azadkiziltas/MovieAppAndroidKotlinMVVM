package com.example.movie.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.movie.data.model.Movie.MovieResponse
import com.example.movie.data.model.People.PeopleResponse
import com.example.movie.data.repository.Search.Movie.SearchMovieRepository
import com.example.movie.data.repository.Search.People.SearchPeopleRepository
import com.example.movie.ui.base.BaseViewModel
import com.example.movie.util.constants.ResourceStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(private val searchMovieRepository: SearchMovieRepository,private val searchPeopleRepository: SearchPeopleRepository)  : BaseViewModel() {

    var searchMovieLiveData = MutableLiveData<MovieResponse>()
    var searchPeopleLiveData = MutableLiveData<PeopleResponse>()


    fun searchMovies(search:String) = viewModelScope.launch {
        searchMovieRepository.searchMovies(search)
            .asLiveData(viewModelScope.coroutineContext).observeForever {
                when (it.status) {
                    ResourceStatus.LOADING -> {
                        loading.postValue(true)
                    }

                    ResourceStatus.SUCCESS -> {
                        searchMovieLiveData.postValue(it.data!!)
                        loading.postValue(false)
                    }

                    ResourceStatus.ERROR -> {

                        error.postValue(it.throwable!!)
                        loading.postValue(false)
                    }
                }
            }
    }

    fun searchPeople(search:String) = viewModelScope.launch {
        searchPeopleRepository.searchPeople(search)
            .asLiveData(viewModelScope.coroutineContext).observeForever {
                when (it.status) {
                    ResourceStatus.LOADING -> {
                        loading.postValue(true)
                    }

                    ResourceStatus.SUCCESS -> {
                        searchPeopleLiveData.postValue(it.data!!)
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