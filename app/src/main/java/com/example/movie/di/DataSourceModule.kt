package com.example.movie.di

import com.example.movie.data.repository.Movie.MovieRemoteDataSource
import com.example.movie.data.repository.TvShow.TvShowRemoteDataSource
import com.example.movie.data.repository.Movie.MovieDataSource
import com.example.movie.data.repository.MovieDetails.MovieDetailsDataSource
import com.example.movie.data.repository.MovieDetails.MovieDetailsRemoteDataSource
import com.example.movie.data.repository.MovieImages.MovieImagesDataSource
import com.example.movie.data.repository.MovieImages.MovieImagesRemoteDataSource
import com.example.movie.data.repository.Search.Movie.SearchMovieDataSource
import com.example.movie.data.repository.Search.Movie.SearchMovieRemoteDataSource
import com.example.movie.data.repository.TvShow.TvShowDataSource

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {



    @Binds
    @Singleton
    abstract fun bindMovieDataSource(
        dataSource: MovieRemoteDataSource
    ): MovieDataSource

    @Binds
    @Singleton
    abstract fun bindTvShowDataSource(
        dataSource: TvShowRemoteDataSource
    ): TvShowDataSource

        @Binds
    @Singleton
    abstract fun bindSearchMovieDataSource(
        dataSource: SearchMovieRemoteDataSource
    ): SearchMovieDataSource


        @Binds
    @Singleton
    abstract fun bindMovieDetailsDataSource(
        dataSource: MovieDetailsRemoteDataSource
    ): MovieDetailsDataSource


        @Binds
    @Singleton
    abstract fun bindMovieImagesDataSource(
        dataSource: MovieImagesRemoteDataSource
    ): MovieImagesDataSource




}
