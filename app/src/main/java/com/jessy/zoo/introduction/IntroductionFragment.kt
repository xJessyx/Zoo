package com.jessy.zoo.introduction

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.jessy.zoo.MainNavigationDirections
import com.jessy.zoo.databinding.FragmentIntroductionBinding
import com.jessy.zoo.ext.getVmFactory
import com.jessy.zoo.home.HomeAdapter

class IntroductionFragment : Fragment() {

    private val viewModel by viewModels<IntroductionViewModel> {
        getVmFactory(IntroductionFragmentArgs.fromBundle(requireArguments()).pavilionKey) }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val binding = FragmentIntroductionBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        val adapter = IntroductionAdapter(IntroductionAdapter.OnClickListener {
            viewModel.displayAnimalDetail(it)
        })
        binding.recyclerIntroduction.adapter = adapter
        binding.recyclerIntroduction.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        viewModel.introductionList.observe(
            viewLifecycleOwner,
            Observer {
                it?.let {

                    viewModel.addAnimalData(it)
                    Log.v("animalList","${viewModel.animalList}")
                    adapter.submitList(viewModel.animalList)

                }
            }
        )
        binding.imgToolbarIntroduction.setOnClickListener {
            this.findNavController().navigateUp()

        }

        binding.tvHrefIntroduction.text = "在網頁開啟 ${viewModel.resultX.value?.e_url}"

        viewModel.navigateToAnimal.observe(
            viewLifecycleOwner,
            Observer {
                it?.let {
                    findNavController().navigate(MainNavigationDirections.navigateToAnimalFragment(it))
                    viewModel.onDetailNavigated()

                }
            }
        )

        return binding.root

    }


}