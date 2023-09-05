package com.example.movie.data.repository.TvShow

import com.example.movie.util.constants.Resource
import com.example.movie.data.model.TvShow.TvShowResponse
import kotlinx.coroutines.flow.Flow

interface TvShowDataSource {
  suspend  fun getAllPopularTvShows(): Flow<Resource<TvShowResponse>>
}
