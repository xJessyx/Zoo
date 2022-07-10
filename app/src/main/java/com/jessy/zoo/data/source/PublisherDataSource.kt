package com.jessy.zoo.data.source

import com.jessy.zoo.data.*

interface PublisherDataSource {
    suspend fun getZoo(): Result<ZooResult>
}