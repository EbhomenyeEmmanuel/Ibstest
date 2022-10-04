package com.emmanull.ibstest.data.api.model

class ApiObj(
    val results: List<ApiResults>?
)

data class ApiResults(
    val name: ApiResultsName?,
    val phone: String?,
    val cell: String?,
    val picture: ApiResultsPicture?,
    val id: ApiResultsId?
)

data class ApiResultsName(
    val title: String,
    val first: String, val last: String
)

data class ApiResultsId(
    val name: String,
    val value: String,
)

data class ApiResultsPicture(
    val large: String?,
    val medium: String?,
    val thumbNail: String?
)