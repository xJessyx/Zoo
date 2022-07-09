package com.jessy.zoo.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jessy.zoo.data.ZooResult
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val HOST_NAME = "data.taipei"
private const val API_VERSION = "v1"
private const val BASE_URL = "https://$HOST_NAME/api/$API_VERSION/"

//private const val HOST_NAME = "drive.google.com"
//private const val BASE_URL = "https://$HOST_NAME/"

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
    .addConverterFactory(MoshiConverterFactory.create(moshi).asLenient())
    .baseUrl(BASE_URL)
    .client(client)
    .build()

interface ZooApiService {

    @GET("dataset/5a0e5fbb-72f8-41c6-908e-2fb25eff9b8a?scope=resourceAquire")
    suspend fun getZoo(): ZooResult

//    @GET("dataset/a3e2b221-75e0-45c1-8f97-75acbd43d613?scope=resourceAquire")
//    suspend fun getAnimal(): AnimalResult

  //  @GET("file/d/105LWiz9tF7DMq0T137eWv3ZnSHR1Ojz9/view?usp=sharing")
  //  suspend fun getZoo(
       // @Query("usp") usp: String? = null
  //  ): ZooResult

//    fun getZoo(
//    ): Call<String>
}
object ZooApi {
    val retrofitService : ZooApiService by lazy {
        retrofit.create(ZooApiService::class.java)
    }

}