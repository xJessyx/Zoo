package com.jessy.zoo.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jessy.zoo.animal.AnimalViewModel
import com.jessy.zoo.data.ResultY
import com.jessy.zoo.data.source.PublisherRepository

@Suppress("UNCHECKED_CAST")
class AnimalViewModelFactory(
    private val publisherRepository: PublisherRepository,
    private val resultY: ResultY,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        with(modelClass) {
            when {
                isAssignableFrom(AnimalViewModel::class.java) ->
                    AnimalViewModel(publisherRepository, resultY)

                else ->
                    throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        } as T
}