package com.example.movie.data.repository.People.PoepleDetails

import com.example.movie.data.model.People.PeopleDetailsResponse
import com.example.movie.util.constants.Constants
import com.example.movie.util.constants.Resource
import com.example.movie.util.network.PlaceHolderApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class PeopleDetailsRemoteDataSource @Inject constructor(private val api: PlaceHolderApi)  :
    PeopleDetailsDataSource {


    // flow
    override suspend fun getPeopleDetails(peopleId:String): Flow<Resource<PeopleDetailsResponse>> = flow  {
        try {
            emit(Resource.Loading())
            val list = api.personDetails(Constants.AUT, Constants.ACCEPT, peopleId)
            emit(Resource.Success(list.body()))
        } catch (e: Exception) {
            emit(Resource.Error(e))
            e.printStackTrace()
        }
    }




}

