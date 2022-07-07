package com.jessy.zoo.network

import android.os.Parcelable
import com.jessy.zoo.Data.DataAll
import com.jessy.zoo.Data.DataResult
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ZooResult(
    val error: String? = null,
    @Json(name = "data") val discounts: List<DataAll>? = null
  //  val data: List<DataResult>

): Parcelable

