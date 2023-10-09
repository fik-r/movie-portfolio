package com.example.data.api.response

import com.google.gson.annotations.SerializedName

data class ResponseSearch(
    @SerializedName("count")
    val totalCount: Int?,
    @SerializedName("results")
    val items: List<ResponseMovie>?
)