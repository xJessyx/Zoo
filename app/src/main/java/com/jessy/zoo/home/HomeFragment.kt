package com.jessy.zoo.home

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.jessy.zoo.R
import com.jessy.zoo.databinding.FragmentHomeBinding
import com.jessy.zoo.ext.getVmFactory
import androidx.lifecycle.Observer


class HomeFragment : Fragment(){

    private val viewModel by viewModels<HomeViewModel> { getVmFactory() }
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_home, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner= this
        val adapter = HomeAdapter(HomeAdapter.OnClickListener {
            viewModel.dispalyPavilionDetail(it)
        })
        binding.recyclerHome.adapter = adapter

        viewModel.discountsList.observe(
            viewLifecycleOwner,
            Observer {
                it?.let {
//                    adapter.submitList(it.discounts)
                }
            }
        )

//        binding.button2.setOnClickListener {
//            this.findNavController()
//                .navigate(HomeFragmentDirections.actionHomeFragmentToIntroductionFragment())
//
//        }
        return binding.root
    }

}

