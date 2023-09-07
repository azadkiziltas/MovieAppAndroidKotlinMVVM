package com.example.movie.data.repository.Movie.MovieDetails

import com.example.movie.data.model.Movie.MovieDetailsResponse
import com.example.movie.util.constants.Constants
import com.example.movie.util.constants.Resource
import com.example.movie.util.network.PlaceHolderApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class MovieDetailsRemoteDataSource @Inject constructor(private val api: PlaceHolderApi)  :
    MovieDetailsDataSource {


    // flow
    override suspend fun getMovieDetails(movieId:String): Flow<Resource<MovieDetailsResponse>> = flow  {
        try {
            emit(Resource.Loading())
            val list = api.movieDetails(Constants.AUT, Constants.ACCEPT, movieId)
            emit(Resource.Success(list.body()))
        } catch (e: Exception) {
            emit(Resource.Error(e))
            e.printStackTrace()
        }
    }




}
