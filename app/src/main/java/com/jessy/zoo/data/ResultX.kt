package com.jessy.zoo.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResultX(
    val _id: Int = 0,
    val _importDate: ImportDate? = null,
    val e_category: String?= "",
    val e_geo: String ?="",
    val e_info: String?="",
    val e_memo: String?="",
    val e_name: String?="",
    val e_no: String?="",
    val e_pic_url: String?="",
    val e_url: String?=""
): Parcelable


//@Parcelize
//data class ResultX(
//    val E_Category: String = "",
//    val E_Geo: String = "",
//    val E_Info: String = "",
//    val E_Memo: String = "",
//    val E_Name: String = "",
//    val E_Pic_URL: String = "",
//    val E_URL: String = "",
//    val E_no: String = "",
//    val _id: Int = 0
//): Parcelable


