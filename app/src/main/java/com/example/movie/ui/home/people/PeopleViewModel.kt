package com.example.movie.ui.home.people

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.movie.data.model.People.PeopleResponse
import com.example.movie.data.repository.People.PeopleRepository
import com.example.movie.ui.base.BaseViewModel
import com.example.movie.util.constants.ResourceStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PeopleViewModel @Inject constructor(private val userRepository: PeopleRepository) :
    BaseViewModel() {
    var allPopularPeopleLiveData = MutableLiveData<PeopleResponse>()

    init {
        getAllPopularPeople("1")
    }

    fun getAllPopularPeople(page:String) = viewModelScope.launch {
        userRepository.getAllPopularPeople(page)
            .asLiveData(viewModelScope.coroutineContext).observeForever {
                when (it.status) {
                    ResourceStatus.LOADING -> {
                        loading.postValue(true)
                    }

                    ResourceStatus.SUCCESS -> {
                        allPopularPeopleLiveData.postValue(it.data!!)
                        Log.d("___", "getAllPopularPeople: "+ it.data!!.results)
                        loading.postValue(false)
                    }

                    ResourceStatus.ERROR -> {

                        error.postValue(it.throwable!!)
                        loading.postValue(false)
                    }
                }
            }
    }
}