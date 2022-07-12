package com.jessy.zoo.data.source

import com.jessy.zoo.data.*

interface PublisherRepository {

    suspend fun getZoo(): Result<ZooResult>
    suspend fun getAnimal(): Result<AnimalResult>

}