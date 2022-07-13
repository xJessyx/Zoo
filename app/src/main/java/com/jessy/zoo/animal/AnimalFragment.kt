package com.jessy.zoo.animal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.jessy.zoo.MainNavigationDirections
import com.jessy.zoo.R
import com.jessy.zoo.databinding.FragmentAnimalBinding
import com.jessy.zoo.databinding.FragmentIntroductionBinding
import com.jessy.zoo.ext.getVmFactory
import com.jessy.zoo.introduction.IntroductionFragmentArgs
import com.jessy.zoo.introduction.IntroductionViewModel

class AnimalFragment : Fragment() {

    private val viewModel by viewModels<AnimalViewModel> {
        getVmFactory(AnimalFragmentArgs.fromBundle(requireArguments()).animalKey) }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        val binding = FragmentAnimalBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.imgToolbarAnimal.setOnClickListener {
            this.findNavController().navigateUp()

        }
        return binding.root
    }

}