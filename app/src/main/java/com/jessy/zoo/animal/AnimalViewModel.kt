package com.jessy.zoo.animal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jessy.zoo.data.ResultX
import com.jessy.zoo.data.ResultY
import com.jessy.zoo.data.source.PublisherRepository

class AnimalViewModel(
    private val publisherRepository: PublisherRepository,
    private val arguments: ResultY)
    : ViewModel() {

    private val _resultY = MutableLiveData<ResultY>().apply {
        value = arguments
    }
    val resultY: LiveData<ResultY>
        get() = _resultY

    }
