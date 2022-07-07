package com.jessy.zoo.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jessy.zoo.network.LoadApiStatus
import com.jessy.zoo.network.ZooApi
import com.jessy.zoo.network.ZooResult
import kotlinx.coroutines.*


class HomeViewModel : ViewModel() {

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

    init {
        getZoo()
//        isCheckWriteRead()
        //  internalRead()
    }

    private fun getZoo(isInitial: Boolean = false) {
        coroutineScope.launch {

            if (isInitial) _status.value = LoadApiStatus.LOADING
            var listResult = ZooApi.retrofitService.getZoo()

            Log.v("result", "${listResult.discounts}")
            Log.v("result", "${listResult.error}")
            Log.v("result", "${LoadApiStatus.LOADING}")

        }
    }
}
