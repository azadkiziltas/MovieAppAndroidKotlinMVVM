package com.example.movie.data.repository.Movie.MovieDetails

import com.example.movie.data.model.Movie.MovieDetailsResponse
import com.example.movie.util.constants.Resource
import kotlinx.coroutines.flow.Flow

interface MovieDetailsDataSource {
  suspend  fun getMovieDetails(movieId:String): Flow<Resource<MovieDetailsResponse>>
}
