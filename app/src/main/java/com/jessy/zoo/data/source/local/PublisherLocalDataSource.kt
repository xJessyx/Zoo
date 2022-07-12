package com.jessy.zoo.data.source.local

import android.content.Context
import com.jessy.zoo.R
import com.jessy.zoo.data.AnimalResult
import com.jessy.zoo.data.Result
import com.jessy.zoo.data.ZooResult
import com.jessy.zoo.data.source.PublisherDataSource
import com.jessy.zoo.network.AnimalApi
import com.jessy.zoo.network.ZooApi
import com.jessy.zoo.util.Util.getString
import com.jessy.zoo.util.Util.isInternetConnected

class PublisherLocalDataSource(val context: Context) : PublisherDataSource {

    override suspend fun getZoo(): Result<ZooResult> {
        if (!isInternetConnected()) {
            return Result.Fail(getString(R.string.internet_not_connected))

        }

        return try {
            // this will run on a thread managed by Retrofit
            val listResult = ZooApi.retrofitService.getZoo()
            listResult.error?.let {
                return Result.Fail(it)
            }
            Result.Success(listResult)
        } catch (e: Exception) {
            //Logger.w("[${this::class.simpleName}] exception=${e.message}")
            Result.Error(e)
        }
    }

    override suspend fun getAnimal(): Result<AnimalResult> {
        if (!isInternetConnected()) {
            return Result.Fail(getString(R.string.internet_not_connected))

        }

        return try {

            val animalResult = AnimalApi.animalRetrofitService.getAnimal()
            animalResult.error?.let {
                return Result.Fail(it)
            }
            Result.Success(animalResult)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

}