package com.jessy.zoo.data.source
import com.jessy.zoo.data.Result
import com.jessy.zoo.data.ZooResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class DefaultPublisherRepository (private val publisherLocalDataSource: PublisherDataSource,
                                  private val publisherRemoteDataSource: PublisherDataSource,
                                  private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : PublisherRepository
{

    override suspend fun getZoo(): Result<ZooResult> {
        return publisherRemoteDataSource.getZoo()
    }
}
