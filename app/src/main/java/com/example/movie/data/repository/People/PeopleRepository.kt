package com.example.movie.data.repository.People

import com.example.movie.util.constants.Resource
import com.example.movie.data.model.People.PeopleResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PeopleRepository @Inject constructor(private  val movieDataSource: PeopleDataSource){

    suspend  fun getAllPopularPeople(page:String): Flow<Resource<PeopleResponse>> {
        return movieDataSource.getAllPopularPeople(page)
    }
}