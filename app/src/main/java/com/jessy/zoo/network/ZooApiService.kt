package com.jessy.zoo.network

import com.jessy.zoo.data.AnimalResult
import com.jessy.zoo.data.ZooResult
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val HOST_NAME = "data.taipei"
private const val API_VERSION = "v1"
private const val BASE_URL = "https://$HOST_NAME/api/$API_VERSION/"

private const val ANIMAL_HOST_NAME = "xjessyx.github.io"
private const val ANIMAL_BASE_URL = "https://$ANIMAL_HOST_NAME/Animal/"


private val moshi = Moshi.Builder()
    .addLast(KotlinJsonAdapterFactory())
    .build()

private val client = OkHttpClient.Builder()
    .addInterceptor(
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    )
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .client(client)
    .build()

private val animalRetrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(ANIMAL_BASE_URL)
    .client(client)
    .build()

interface ZooApiService {
    @GET("dataset/5a0e5fbb-72f8-41c6-908e-2fb25eff9b8a?scope=resourceAquire")
    suspend fun getZoo(): ZooResult

}

interface AnimalApiService {
    @GET("animal.json")
    suspend fun getAnimal(): AnimalResult
}

object ZooApi {
    val retrofitService: ZooApiService by lazy {
        retrofit.create(ZooApiService::class.java)
    }
}

object AnimalApi {
    val animalRetrofitService: AnimalApiService by lazy {
        animalRetrofit.create(AnimalApiService::class.java)
    }

}
