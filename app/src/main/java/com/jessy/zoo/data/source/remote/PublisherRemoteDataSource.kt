package com.jessy.zoo.data.source.remote
import com.jessy.zoo.R
import com.jessy.zoo.data.Result
import com.jessy.zoo.data.ZooResult
import com.jessy.zoo.data.source.PublisherDataSource
import com.jessy.zoo.network.ZooApi
import com.jessy.zoo.util.Logger
import com.jessy.zoo.util.Util.getString
import com.jessy.zoo.util.Util.isInternetConnected

object PublisherRemoteDataSource  : PublisherDataSource {

//    suspend fun getZoo(): ZooResult
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
}