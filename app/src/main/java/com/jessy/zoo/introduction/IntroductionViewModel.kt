package com.jessy.zoo.introduction

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jessy.zoo.data.ResultX
import com.jessy.zoo.data.source.PublisherRepository

class IntroductionViewModel(
    private val publisherRepository: PublisherRepository,
    private val arguments: ResultX) : ViewModel() {
    private val _resultX = MutableLiveData<ResultX>().apply {
        value = arguments
    }
    val resultX: LiveData<ResultX>
        get() = _resultX
}