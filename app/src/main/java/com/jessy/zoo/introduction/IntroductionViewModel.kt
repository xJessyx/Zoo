package com.jessy.zoo.introduction

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jessy.zoo.R
import com.jessy.zoo.data.*
import com.jessy.zoo.data.source.PublisherRepository
import com.jessy.zoo.network.LoadApiStatus
import com.jessy.zoo.util.Util
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class IntroductionViewModel(
    private val publisherRepository: PublisherRepository,
    private val arguments: ResultX,
) : ViewModel() {

    var animalList = mutableListOf<ResultY>()

    private val _resultX = MutableLiveData<ResultX>().apply {
        value = arguments
    }
    val resultX: LiveData<ResultX>
        get() = _resultX

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val _status = MutableLiveData<LoadApiStatus>()
    val status: LiveData<LoadApiStatus>
        get() = _status

    private val _introductionList = MutableLiveData<AnimalResult>()
    val introductionList: LiveData<AnimalResult>
        get() = _introductionList

    private val _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error

    private val _refreshStatus = MutableLiveData<Boolean>()
    val refreshStatus: LiveData<Boolean>
        get() = _refreshStatus

    private val _navigateToAnimal = MutableLiveData<ResultY>()
    val navigateToAnimal: MutableLiveData<ResultY>
        get() = _navigateToAnimal

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    init {
        getAnimal()
    }

    private fun getAnimal(isInitial: Boolean = false) {
        coroutineScope.launch {

            val introductionResult = publisherRepository.getAnimal()

            _introductionList.value = when (introductionResult) {

                is Result.Success -> {
                    _error.value = null
                    if (isInitial) _status.value = LoadApiStatus.DONE
                    introductionResult.data
                }
                is Result.Fail -> {
                    _error.value = introductionResult.error
                    if (isInitial) _status.value = LoadApiStatus.ERROR
                    null
                }
                is Result.Error -> {
                    _error.value = introductionResult.exception.toString()
                    if (isInitial) _status.value = LoadApiStatus.ERROR
                    null
                }
                else -> {
                    _error.value = Util.getString(R.string.you_know_nothing)
                    if (isInitial) _status.value = LoadApiStatus.ERROR
                    null
                }
            }
            _refreshStatus.value = false

        }
    }

    fun displayAnimalDetail(resultY: ResultY) {
        _navigateToAnimal.value = resultY
    }

    fun onDetailNavigated() {
        _navigateToAnimal.value = null
    }

    fun addAnimalData(data: AnimalResult) {
        data.discounts?.results?.let{
            for (hot in data.discounts.results) {
//                if(hot.A_Location == resultX.value?.e_name){
//                    animalList.add(hot)
//                }
//                Log.v("hot.A_Location == resultX.value?.e_name","hot.A_Location = ${hot.A_Location} == ${resultX.value?.e_name}")

                animalList.add(hot)
            }
        }
    }
}
