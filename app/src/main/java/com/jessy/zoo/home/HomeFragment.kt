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
import com.jessy.zoo.data.ZooResult
import com.jessy.zoo.databinding.FragmentHomeBinding
import com.jessy.zoo.ext.getVmFactory
import com.jessy.zoo.network.ZooApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment(){
//    private val viewModel: HomeViewModel by lazy {
//        ViewModelProvider(this).get(HomeViewModel::class.java)
//    }

    private val viewModel by viewModels<HomeViewModel> { getVmFactory() }
    @SuppressLint("SimpleDateFormat", "NotifyDataSetChanged")
private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_home, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner= this

        binding.button2.setOnClickListener {
            this.findNavController()
                .navigate(HomeFragmentDirections.actionHomeFragmentToIntroductionFragment())

//            ZooApi.retrofitService.getZoo().enqueue(object : Callback<String> {
//                override fun onResponse(
//                    call: Call<String>,
//                    response: Response<String>
//                ) {
//                    Log.d(TAG, "response: ${response.body().toString()}")
//                }
//                override fun onFailure(call: Call<String>, t: Throwable) {
//                    Log.d(TAG, "error: ${t.message}" ?: "Get some error")
//                }
//            })

        }
        return binding.root
    }

}

//private fun Any.enqueue(any: Any) {
//
//}

