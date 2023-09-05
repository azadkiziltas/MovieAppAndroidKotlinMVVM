package com.example.movie.data.repository.Search.Movie

import com.example.movie.util.constants.Resource
import com.example.movie.util.constants.Constants
import com.example.movie.data.model.Movie.MovieResponse
import com.example.movie.util.network.PlaceHolderApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class SearchMovieRemoteDataSource @Inject constructor(private val api: PlaceHolderApi)  :
    SearchMovieDataSource {


    // flow
    override suspend fun searchMovies(search:String): Flow<Resource<MovieResponse>> = flow  {
        try {
            emit(Resource.Loading())
            val list = api.searchMovies(Constants.AUT, Constants.ACCEPT,search)
            emit(Resource.Success(list.body()))
        } catch (e: Exception) {
            emit(Resource.Error(e))
            e.printStackTrace()
        }
    }
}
