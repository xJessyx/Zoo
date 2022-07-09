package com.jessy.zoo.data.source
import com.jessy.zoo.data.Result
import com.jessy.zoo.data.ZooResult
interface PublisherRepository {

    suspend fun getZoo(): Result<ZooResult>

}