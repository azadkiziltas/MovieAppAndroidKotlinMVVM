package com.example.movie.di

import com.example.movie.data.repository.Movie.MovieDataSource
import com.example.movie.data.repository.Movie.MovieDetails.MovieDetailsDataSource
import com.example.movie.data.repository.Movie.MovieDetails.MovieDetailsRemoteDataSource
import com.example.movie.data.repository.Movie.MovieImages.MovieImagesDataSource
import com.example.movie.data.repository.Movie.MovieImages.MovieImagesRemoteDataSource
import com.example.movie.data.repository.Movie.MovieRemoteDataSource
import com.example.movie.data.repository.People.PeopleDataSource
import com.example.movie.data.repository.People.PeopleRemoteDataSource
import com.example.movie.data.repository.People.PoepleDetails.PeopleDetailsDataSource
import com.example.movie.data.repository.People.PoepleDetails.PeopleDetailsRemoteDataSource
import com.example.movie.data.repository.Search.Movie.SearchMovieDataSource
import com.example.movie.data.repository.Search.Movie.SearchMovieRemoteDataSource
import com.example.movie.data.repository.Search.People.SearchPeopleDataSource
import com.example.movie.data.repository.Search.People.SearchPeopleRemoteDataSource
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


        @Binds
    @Singleton
    abstract fun bindPeopleDataSource(
        dataSource: PeopleRemoteDataSource
    ): PeopleDataSource


        @Binds
    @Singleton
    abstract fun bindPeopleDetailsDataSource(
        dataSource: PeopleDetailsRemoteDataSource
    ): PeopleDetailsDataSource


    @Binds
    @Singleton
    abstract fun bindSearchPeopleDataSource(
        dataSource: SearchPeopleRemoteDataSource
    ): SearchPeopleDataSource


}
