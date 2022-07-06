package com.jessy.zoo

data class Data(
    val result: Result
)

data class Result(
    val count: Int,
    val limit: Int,
    val offset: Int,
    val results: List<ResultX>,
    val sort: String
)

data class ResultX(
    val _id: Int,
    val _importDate: ImportDate,
    val e_category: String,
    val e_geo: String,
    val e_info: String,
    val e_memo: String,
    val e_name: String,
    val e_no: String,
    val e_pic_url: String,
    val e_url: String
)

data class ImportDate(
    val date: String,
    val timezone: String,
    val timezone_type: Int
)
