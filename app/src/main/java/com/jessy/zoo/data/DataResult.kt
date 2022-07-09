package com.jessy.zoo.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize

data class DataResult(
    val result: DataAll
): Parcelable