package com.jessy.zoo.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class DataAll(
    val count: Int = 0,
    val limit: Int= 0,
    val offset: Int = 0,
    val results: List<ResultX> ?=null ,
    val sort: String = ""
): Parcelable


