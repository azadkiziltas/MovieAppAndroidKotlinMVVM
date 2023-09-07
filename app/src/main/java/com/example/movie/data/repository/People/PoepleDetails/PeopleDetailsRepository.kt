package com.example.movie.data.repository.People.PoepleDetails

import com.example.movie.data.model.People.PeopleDetailsResponse
import com.example.movie.util.constants.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PeopleDetailsRepository @Inject constructor(private  val peopleDataSource: PeopleDetailsDataSource){

    suspend  fun getPeopleDetails(peopleId:String): Flow<Resource<PeopleDetailsResponse>> {
        return peopleDataSource.getPeopleDetails(peopleId)
    }
}