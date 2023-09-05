package com.example.movie.data.repository.Movie

import com.example.movie.util.constants.Resource
import com.example.movie.data.model.Movie.MovieResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(private  val movieDataSource: MovieDataSource){

    suspend  fun getAllPopularMovies(): Flow<Resource<MovieResponse>> {
        return movieDataSource.getAllPopularMovies()
    }
}