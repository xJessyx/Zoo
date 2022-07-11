package com.jessy.zoo.home

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jessy.zoo.R
import com.jessy.zoo.network.LoadApiStatus
import com.jessy.zoo.network.ZooApi
import com.jessy.zoo.data.*
import com.jessy.zoo.data.source.PublisherRepository
import com.jessy.zoo.util.Util.getString
import kotlinx.coroutines.*


class HomeViewModel(private val publisherRepository: PublisherRepository): ViewModel(){


    var pavilionList = mutableListOf<ResultX>()

    // Create a Coroutine scope using a job to be able to cancel when needed
    private var viewModelJob = Job()

    // the Coroutine runs using the Main (UI) dispatcher
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    // status: The internal MutableLiveData that stores the status of the most recent request
    private val _status = MutableLiveData<LoadApiStatus>()

    val status: LiveData<LoadApiStatus>
        get() = _status

    private val _discountsList = MutableLiveData<ZooResult>()
    val discountsList: LiveData<ZooResult>
        get() = _discountsList

    private val _error = MutableLiveData<String>()

    val error: LiveData<String>
        get() = _error

    private val _refreshStatus = MutableLiveData<Boolean>()

    val refreshStatus: LiveData<Boolean>
        get() = _refreshStatus

    private val _navigateToSelectedProperty = MutableLiveData<ResultX>()
    val navigateToSelectedProperty: MutableLiveData<ResultX>
        get() = _navigateToSelectedProperty

    init {
        getZoo()
        // getAnimal()
    }

    private fun getZoo(isInitial: Boolean = false) {
        coroutineScope.launch {

            if (isInitial) _status.value = LoadApiStatus.LOADING
//            var listResult = ZooApi.retrofitService.getZoo()
            val discountsResult = publisherRepository.getZoo()
            Log.v("result_discountsResult", "${discountsResult.toString()}")
//            Log.v("result", "${discountsResult.error}")
            Log.v("result_LoadApiStatus", "${LoadApiStatus.LOADING}")

            _discountsList.value = when (discountsResult) {

                is Result.Success -> {
                    _error.value = null
                    if (isInitial) _status.value = LoadApiStatus.DONE
                    discountsResult.data

                }
                is Result.Fail -> {
                    _error.value = discountsResult.error
                    if (isInitial) _status.value = LoadApiStatus.ERROR
                    null
                }
                is Result.Error -> {
                    _error.value = discountsResult.exception.toString()
                    if (isInitial) _status.value = LoadApiStatus.ERROR
                    null
                }
                else -> {
                    _error.value = getString(R.string.you_know_nothing)
                    if (isInitial) _status.value = LoadApiStatus.ERROR
                    null
                }
            }
            _refreshStatus.value = false

        }

   }

    fun dispalyPavilionDetail(resultX: ResultX) {
        _navigateToSelectedProperty.value = resultX
    }

    fun addDiscountsData(data: ZooResult) {

        for (hot in data.discounts?.results!!) {
            pavilionList.add(hot)

        }
        Log.v("pavilionList","$pavilionList")
    }


}
//    private fun getAnimal(isInitial: Boolean = false) {
//        coroutineScope.launch {
//
//            if (isInitial) _status.value = LoadApiStatus.LOADING
//            var listResult = ZooApi.retrofitService.getAnimal()
//
//            Log.v("result", "${listResult.discounts?.results}")
//            Log.v("result", "${listResult.error}")
//            Log.v("result", "${LoadApiStatus.LOADING}")
//
//        }
//  }


//}
