package com.jessy.zoo.util

import android.content.Context
import androidx.annotation.VisibleForTesting
import com.jessy.zoo.data.source.DefaultPublisherRepository
import com.jessy.zoo.data.source.PublisherDataSource
import com.jessy.zoo.data.source.PublisherRepository
import com.jessy.zoo.data.source.local.PublisherLocalDataSource
import com.jessy.zoo.data.source.remote.PublisherRemoteDataSource

object ServiceLocator {

    @Volatile
    var publisherRepository: PublisherRepository? = null
        @VisibleForTesting set

    fun provideTasksRepository(context: Context): PublisherRepository {
        synchronized(this) {
            return publisherRepository
                ?: publisherRepository
                ?: createStylishRepository(context)
        }
    }

    private fun createStylishRepository(context: Context): PublisherRepository {
        return DefaultPublisherRepository(
            PublisherRemoteDataSource,
            createLocalDataSource(context)
        )
    }

    private fun createLocalDataSource(context: Context): PublisherDataSource {
        return PublisherLocalDataSource(context)
    }
}
