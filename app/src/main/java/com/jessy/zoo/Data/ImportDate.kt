package com.jessy.zoo.Data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ImportDate(
    val date: String,
    val timezone: String,
    val timezone_type: Int
): Parcelable
