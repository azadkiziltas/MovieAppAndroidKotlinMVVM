package com.example.movie.data.repository.Movie.MovieDetails

import com.example.movie.data.model.Movie.MovieDetailsResponse
import com.example.movie.util.constants.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieDetailsRepository @Inject constructor(private  val movieDataSource: MovieDetailsDataSource){

    suspend  fun getMovieDetails(movieId:String): Flow<Resource<MovieDetailsResponse>> {
        return movieDataSource.getMovieDetails(movieId)
    }
}