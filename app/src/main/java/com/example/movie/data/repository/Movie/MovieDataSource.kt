package com.example.movie.data.repository.Movie

import com.example.movie.util.constants.Resource
import com.example.movie.data.model.Movie.MovieResponse
import kotlinx.coroutines.flow.Flow

interface MovieDataSource {
  suspend  fun getAllPopularMovies(): Flow<Resource<MovieResponse>>
}
