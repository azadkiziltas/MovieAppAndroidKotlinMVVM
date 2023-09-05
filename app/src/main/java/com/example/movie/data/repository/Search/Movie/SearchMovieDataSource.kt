package com.example.movie.data.repository.Search.Movie

import com.example.movie.util.constants.Resource
import com.example.movie.data.model.Movie.MovieResponse
import kotlinx.coroutines.flow.Flow

interface SearchMovieDataSource {
  suspend  fun searchMovies(search:String): Flow<Resource<MovieResponse>>
}
