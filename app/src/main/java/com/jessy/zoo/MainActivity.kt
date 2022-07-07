package com.jessy.zoo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.jessy.zoo.network.ZooApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

}