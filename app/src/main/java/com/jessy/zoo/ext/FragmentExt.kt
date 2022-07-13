package com.jessy.zoo.ext

import androidx.fragment.app.Fragment
import com.jessy.zoo.ViewModelFactory
import com.jessy.zoo.ZooApplication
import com.jessy.zoo.data.ResultX
import com.jessy.zoo.data.ResultY
import com.jessy.zoo.factory.AnimalViewModelFactory
import com.jessy.zoo.factory.IntroductionViewModelFactory


fun Fragment.getVmFactory(): ViewModelFactory {
    val repository = (requireContext().applicationContext as ZooApplication).publisherRepository
    return ViewModelFactory(repository)
}

fun Fragment.getVmFactory(resultX: ResultX): IntroductionViewModelFactory {
    val repository = (requireContext().applicationContext as ZooApplication).publisherRepository
    return IntroductionViewModelFactory(repository, resultX)

}

fun Fragment.getVmFactory(resultY: ResultY): AnimalViewModelFactory {
    val repository = (requireContext().applicationContext as ZooApplication).publisherRepository
    return AnimalViewModelFactory(repository, resultY)

}