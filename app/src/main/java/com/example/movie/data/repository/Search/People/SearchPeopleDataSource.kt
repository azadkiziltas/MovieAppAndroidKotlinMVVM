package com.example.movie.data.repository.Search.People

import com.example.movie.data.model.People.PeopleResponse
import com.example.movie.util.constants.Resource
import kotlinx.coroutines.flow.Flow

interface SearchPeopleDataSource {
  suspend  fun searchPeople(search:String): Flow<Resource<PeopleResponse>>
}
