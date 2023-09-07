package com.example.movie.ui.peopleDetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.movie.data.model.People.PeopleDetailsResponse
import com.example.movie.data.repository.People.PoepleDetails.PeopleDetailsRepository
import com.example.movie.ui.base.BaseViewModel
import com.example.movie.util.constants.ResourceStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PeopleDetailsViewModel @Inject constructor(private val detailsRepository: PeopleDetailsRepository)  : BaseViewModel() {

    var peopleDetailsLiveData = MutableLiveData<PeopleDetailsResponse>()


    fun getMovieDetails(peopleId:String) = viewModelScope.launch {
        detailsRepository.getPeopleDetails(peopleId)
            .asLiveData(viewModelScope.coroutineContext).observeForever {
                when (it.status) {
                    ResourceStatus.LOADING -> {
                        loading.postValue(true)
                    }

                    ResourceStatus.SUCCESS -> {
                        peopleDetailsLiveData.postValue(it.data!!)
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