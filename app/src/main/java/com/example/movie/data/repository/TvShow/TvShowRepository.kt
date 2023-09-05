package com.example.movie.data.repository.TvShow

import com.example.movie.util.constants.Resource
import com.example.movie.data.model.TvShow.TvShowResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TvShowRepository @Inject constructor(private  val movieDataSource: TvShowDataSource){

    suspend  fun getAllPopularTvShow(): Flow<Resource<TvShowResponse>> {
        return movieDataSource.getAllPopularTvShows()
    }
}