package com.example.movie.data.repository.Search.People

import com.example.movie.data.model.People.PeopleResponse
import com.example.movie.util.constants.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchPeopleRepository @Inject constructor(private  val movieDataSource: SearchPeopleDataSource){

    suspend  fun searchPeople(search:String): Flow<Resource<PeopleResponse>> {
        return movieDataSource.searchPeople(search)
    }
}