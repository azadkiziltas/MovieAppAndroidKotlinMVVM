package com.example.movie.data.repository.MovieImages

import com.example.movie.data.model.Movie.MovieDetailsResponse
import com.example.movie.data.model.Movie.MovieImagesResponse
import com.example.movie.util.constants.Resource
import com.example.movie.data.model.Movie.MovieResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieImagesRepository @Inject constructor(private  val movieDataSource: MovieImagesDataSource){

    suspend  fun getMovieImages(movieId:String): Flow<Resource<MovieImagesResponse>> {
        return movieDataSource.getMovieImages(movieId)
    }
}