package com.jessy.zoo

import android.app.Application
import com.jessy.zoo.data.source.PublisherRepository
import com.jessy.zoo.util.ServiceLocator
import kotlin.properties.Delegates

class ZooApplication : Application() {

    val publisherRepository: PublisherRepository
        get() = ServiceLocator.provideTasksRepository(this)

    companion object {
        var instance: ZooApplication by Delegates.notNull()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}