package com.jessy.zoo.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AnimalAll(
    val count: Int = 0 ,
    val limit: Int = 0,
    val offset: Int = 0,
    val results: List<ResultY> ?=null,
    val sort: String =""
): Parcelable