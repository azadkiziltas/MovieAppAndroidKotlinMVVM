package com.example.movie.data.repository.People

import com.example.movie.util.constants.Resource
import com.example.movie.util.constants.Constants
import com.example.movie.data.model.People.PeopleResponse
import com.example.movie.util.network.PlaceHolderApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class PeopleRemoteDataSource @Inject constructor(private val api: PlaceHolderApi)  :
    PeopleDataSource {


    // flow
    override suspend fun getAllPopularPeople(page:String): Flow<Resource<PeopleResponse>> = flow  {
        try {
            emit(Resource.Loading())
            val list = api.getAllPopularPeople(Constants.API_KEY, Constants.ACCEPT,page)
            emit(Resource.Success(list.body()))
        } catch (e: Exception) {
            emit(Resource.Error(e))
            e.printStackTrace()
        }
    }
}
