package com.jessy.zoo.data.source

import android.util.Log
import com.jessy.zoo.data.*
import com.jessy.zoo.data.source.remote.PublisherRemoteDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class DefaultPublisherRepository(
    private val publisherLocalDataSource: PublisherDataSource,
    private val publisherRemoteDataSource: PublisherDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : PublisherRepository {

    override suspend fun getZoo(): Result<ZooResult> {
        return publisherRemoteDataSource.getZoo()
    }

    override suspend fun getAnimal(): Result<AnimalResult> {
        return publisherRemoteDataSource.getAnimal()
    }
}
