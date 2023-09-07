package com.example.movie.data.repository.Search.People

import com.example.movie.data.model.People.PeopleResponse
import com.example.movie.util.constants.Constants
import com.example.movie.util.constants.Resource
import com.example.movie.util.network.PlaceHolderApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class SearchPeopleRemoteDataSource @Inject constructor(private val api: PlaceHolderApi)  :
    SearchPeopleDataSource {


    // flow
    override suspend fun searchPeople(search:String): Flow<Resource<PeopleResponse>> = flow  {
        try {
            emit(Resource.Loading())
            val list = api.searchPerson(Constants.AUT, Constants.ACCEPT,search)
            emit(Resource.Success(list.body()))
        } catch (e: Exception) {
            emit(Resource.Error(e))
            e.printStackTrace()
        }
    }
}
