package com.example.movie.util.network

import com.example.movie.data.model.Movie.MovieResponse
import com.example.movie.data.model.TvShow.TvShowResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface PlaceHolderApi {
    @GET("movie/popular")
    suspend fun getAllPopularMovies(
        @Header("Authorization") authorization: String,
        @Header("accept") accept: String,
    ): Response<MovieResponse>


        @GET("tv/popular")
    suspend fun getAllPopularTvShows(
        @Header("Authorization") authorization: String,
        @Header("accept") accept: String,
    ): Response<TvShowResponse>


        @GET("search/movie")
    suspend fun searchMovies(
        @Header("Authorization") authorization: String,
        @Header("accept") accept: String,

        @Query("query") searchText: String
    ): Response<MovieResponse>




}
