package com.jessy.zoo.data

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AnimalResult(
    val error: String? = null,
    @Json(name = "result") val discounts: AnimalAll? = null
) : Parcelable
