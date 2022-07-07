package com.jessy.zoo.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val HOST_NAME = "data.taipei"
private const val API_VERSION = "v1"
private const val BASE_URL = "https://$HOST_NAME/api/$API_VERSION/"

//
//https: //data.taipei/api/v1/dataset/5a0e5fbb-72f8-41c6-908e-2fb25eff9b8a?scope=resourceAquire
//
//https: //data.taipei/api/v1/dataset/a3e2b221-75e0-45c1-8f97-75acbd43d613?scope=resourceAquire


private val client = OkHttpClient.Builder()
    .addInterceptor(
        HttpLoggingInterceptor().apply {
            level =  HttpLoggingInterceptor.Level.BODY
        }
    )
    .build()
private val moshi = Moshi.Builder()
    .addLast(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .client(client)
    .build()

interface ZooApiService {

    @GET("dataset/5a0e5fbb-72f8-41c6-908e-2fb25eff9b8a?scope=resourceAquire")
    suspend fun getZoo(): ZooResult


}
object ZooApi {
    val retrofitService : ZooApiService by lazy { retrofit.create(ZooApiService::class.java) }
}