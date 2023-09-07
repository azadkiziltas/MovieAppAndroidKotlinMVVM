package com.example.movie.data.repository.People.PoepleDetails

import com.example.movie.data.model.People.PeopleDetailsResponse
import com.example.movie.util.constants.Resource
import kotlinx.coroutines.flow.Flow

interface PeopleDetailsDataSource {
  suspend  fun getPeopleDetails(movieId:String): Flow<Resource<PeopleDetailsResponse>>
}
