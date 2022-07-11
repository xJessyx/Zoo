package com.jessy.zoo.home


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jessy.zoo.R
import com.jessy.zoo.network.LoadApiStatus
import com.jessy.zoo.data.*
import com.jessy.zoo.data.source.PublisherRepository
import com.jessy.zoo.util.Util.getString
import kotlinx.coroutines.*


class HomeViewModel(private val publisherRepository: PublisherRepository): ViewModel(){


    var pavilionList = mutableListOf<ResultX>()

    private var viewModelJob = Job()

    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

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

    private val _navigateToIntroduction = MutableLiveData<ResultX>()
    val navigateToIntroduction: MutableLiveData<ResultX>
        get() = _navigateToIntroduction

    init {
        getZoo(true)
        // getAnimal()
    }

    private fun getZoo(isInitial: Boolean = false) {
        coroutineScope.launch {

            if (isInitial) _status.value = LoadApiStatus.LOADING
            val discountsResult = publisherRepository.getZoo()

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
        _navigateToIntroduction.value = resultX
    }

    fun onDetailNavigated() {
        _navigateToIntroduction.value = null
    }

    fun addDiscountsData(data: ZooResult) {


       data.discounts?.results?.let{
            for (hot in data.discounts.results) {
                pavilionList.add(hot)

            }
        }
    }

    fun refresh() {
        if (status.value != LoadApiStatus.LOADING) {
            getZoo()
        }
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

