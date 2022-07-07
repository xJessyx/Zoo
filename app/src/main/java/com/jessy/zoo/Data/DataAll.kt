package com.jessy.zoo.Data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class DataAll(
    val count: Int,
    val limit: Int,
    val offset: Int,
    val results: List<ResultX>,
    val sort: String
): Parcelable



