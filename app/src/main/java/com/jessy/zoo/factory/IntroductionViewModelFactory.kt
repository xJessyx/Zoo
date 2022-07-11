package com.jessy.zoo.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jessy.zoo.data.ResultX
import com.jessy.zoo.data.source.PublisherRepository
import com.jessy.zoo.introduction.IntroductionViewModel

@Suppress("UNCHECKED_CAST")
class IntroductionViewModelFactory(
    private val publisherRepository: PublisherRepository,
    private val resultX: ResultX
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T =
            with(modelClass) {
                when {
                    isAssignableFrom(IntroductionViewModel::class.java) ->
                        IntroductionViewModel(publisherRepository, resultX)

                    else ->
                        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
                }
            } as T
}