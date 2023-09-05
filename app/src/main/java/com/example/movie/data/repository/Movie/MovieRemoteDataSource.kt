package com.example.movie.data.repository.Movie

import com.example.movie.util.constants.Resource
import com.example.movie.util.constants.Constants
import com.example.movie.data.model.Movie.MovieResponse
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
            val list = api.getAllPopularMovies(Constants.AUT, Constants.ACCEPT)
            emit(Resource.Success(list.body()))
        } catch (e: Exception) {
            emit(Resource.Error(e))
            e.printStackTrace()
        }
    }
}

//@Module
//@InstallIn(SingletonComponent::class)
//object NetworkModule {
//
//    @Singleton
//    @Provides
//    fun provideRetrofit(): Retrofit {
//        return Retrofit
//            .Builder()
//            .baseUrl("https://thesimpsonsquoteapi.glitch.me")
//            .addConverterFactory(GsonConverterFactory.create()).build()
//    }
//
//    @Singleton
//    @Provides
//    fun provideGetQuotes(apiQuote : DataQuoteApi): QuoteRepository {
//        return QuoteRepository(apiQuote)
//    }
//
//}
//
//@Module
//@InstallIn(SingletonComponent::class)
//object NetworkModule {
//
//    @Singleton
//    @Provides
//    fun provideRetrofit(): Retrofit {
//        return Retrofit
//            .Builder()
//            .baseUrl("https://thesimpsonsquoteapi.glitch.me")
//            .addConverterFactory(GsonConverterFactory.create()).build()
//    }
//
//    // You provide retrofit so dagger use it in this method
//    @Singleton
//    @Provides
//    fun provideGetQuotes(retrofit: Retrofit): DataQuoteApi {
//        return retrofit.create(DataQuoteApi::class.java)
//    }
//
//}