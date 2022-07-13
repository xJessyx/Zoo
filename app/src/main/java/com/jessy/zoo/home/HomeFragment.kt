package com.jessy.zoo.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.jessy.zoo.MainNavigationDirections
import com.jessy.zoo.R
import com.jessy.zoo.databinding.FragmentHomeBinding
import com.jessy.zoo.ext.getVmFactory


class HomeFragment : Fragment(){

    private val viewModel by viewModels<HomeViewModel> { getVmFactory() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        val binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.viewModel = viewModel
        binding.lifecycleOwner= viewLifecycleOwner
        val adapter = HomeAdapter(HomeAdapter.OnClickListener {
            viewModel.displayPavilionDetail(it)
        })
        binding.recyclerHome.adapter = adapter
        binding.recyclerHome.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        binding.layoutSwipeRefreshHome.setOnRefreshListener {
            viewModel.refresh()
        }

        viewModel.discountsList.observe(
            viewLifecycleOwner,
            Observer {
                it?.let {

                    viewModel.addDiscountsData(it)
                  adapter.submitList(viewModel.pavilionList)

                }
            }
        )

        viewModel.navigateToIntroduction.observe(
            viewLifecycleOwner,
            Observer {
                it?.let {
                    findNavController().navigate(MainNavigationDirections.navigateToIntroductionFragment(it))
                    viewModel.onDetailNavigated()

                }
            }
        )

        viewModel.refreshStatus.observe(
            viewLifecycleOwner,
            Observer {
                it?.let {
                    binding.layoutSwipeRefreshHome.isRefreshing = it
                }
            }
        )


        return binding.root
    }

}

