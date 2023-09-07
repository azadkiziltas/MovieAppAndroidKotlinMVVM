package com.example.movie.data.repository.Movie.MovieImages

import com.example.movie.data.model.Movie.MovieDetailsResponse
import com.example.movie.data.model.Movie.MovieImagesResponse
import com.example.movie.util.constants.Resource
import com.example.movie.data.model.Movie.MovieResponse
import kotlinx.coroutines.flow.Flow

interface MovieImagesDataSource {
  suspend  fun getMovieImages(movieId:String): Flow<Resource<MovieImagesResponse>>
}
