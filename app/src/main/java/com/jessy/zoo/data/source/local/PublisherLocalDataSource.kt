package com.jessy.zoo.data.source.local

import android.content.Context
import com.jessy.zoo.data.Result
import com.jessy.zoo.data.ZooResult
import com.jessy.zoo.data.source.PublisherDataSource

class PublisherLocalDataSource(val context: Context)  : PublisherDataSource {
    override suspend fun getZoo(): Result<ZooResult> {
        TODO("Not yet implemented")
    }
}