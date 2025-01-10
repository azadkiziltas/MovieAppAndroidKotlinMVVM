package com.example.movie.data.repository.Movie

import com.example.movie.data.model.Movie.MovieResponse
import com.example.movie.util.constants.Constants
import com.example.movie.util.constants.Resource
import com.example.movie.util.network.PlaceHolderApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class MovieRemoteDataSource @Inject constructor(private val api: PlaceHolderApi)  :
    MovieDataSource {


    // flow
    override suspend fun getAllPopularMovies(): Flow<Resource<MovieResponse>> = flow  {
        try {
            emit(Resource.Loading())
            val list = api.getAllPopularMovies(Constants.API_KEY, Constants.ACCEPT)
            emit(Resource.Success(list.body()))
        } catch (e: Exception) {
            emit(Resource.Error(e))
            e.printStackTrace()
        }
    }
}

