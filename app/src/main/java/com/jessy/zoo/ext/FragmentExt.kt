package com.jessy.zoo.ext

import androidx.fragment.app.Fragment
import com.jessy.zoo.ViewModelFactory
import com.jessy.zoo.ZooApplication


    fun Fragment.getVmFactory(): ViewModelFactory {
        val repository = (requireContext().applicationContext as ZooApplication).publisherRepository
        return ViewModelFactory(repository)
    }
