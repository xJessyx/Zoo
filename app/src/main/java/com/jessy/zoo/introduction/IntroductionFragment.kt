package com.jessy.zoo.introduction

import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.jessy.zoo.MainActivity
import com.jessy.zoo.databinding.FragmentIntroductionBinding
import com.jessy.zoo.ext.getVmFactory

class IntroductionFragment : Fragment() {

    private val viewModel by viewModels<IntroductionViewModel> {
        getVmFactory(IntroductionFragmentArgs.fromBundle(requireArguments()).pavilionKey) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val binding = FragmentIntroductionBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.imgToolbarIntroduction.setOnClickListener {
            this.findNavController().navigateUp()

        }
//        binding.tvHrefIntroduction.setText(Html.fromHtml("在網頁開啟<a href=${viewModel.resultX.value?.e_url}</a>"))
//        Log.v("a","${viewModel.resultX.value?.e_url}")
        binding.tvHrefIntroduction.setText("在網頁開啟 ${viewModel.resultX.value?.e_url}")

        return binding.root
    }

}