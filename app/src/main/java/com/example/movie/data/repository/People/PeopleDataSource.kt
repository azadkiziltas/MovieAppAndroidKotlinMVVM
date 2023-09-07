package com.example.movie.data.repository.People

import com.example.movie.util.constants.Resource
import com.example.movie.data.model.Movie.MovieResponse
import com.example.movie.data.model.People.PeopleResponse
import kotlinx.coroutines.flow.Flow

interface PeopleDataSource {
  suspend  fun getAllPopularPeople(page:String): Flow<Resource<PeopleResponse>>
}
