package com.example.movie.data.repository.Search.Movie

import com.example.movie.util.constants.Resource
import com.example.movie.data.model.Movie.MovieResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchMovieRepository @Inject constructor(private  val movieDataSource: SearchMovieDataSource){

    suspend  fun searchMovies(search:String): Flow<Resource<MovieResponse>> {
        return movieDataSource.searchMovies(search)
    }
}