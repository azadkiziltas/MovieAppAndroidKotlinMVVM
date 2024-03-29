package com.example.movie.ui.home.watchlist

import com.example.movie.data.repository.Movie.MovieRepository
import com.example.movie.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WatchListViewModel @Inject constructor(private val userRepository: MovieRepository) :
    BaseViewModel() {
}