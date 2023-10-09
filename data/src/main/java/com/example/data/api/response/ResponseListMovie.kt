package com.example.data.api.response

import com.google.gson.annotations.SerializedName

data class ResponseListMovie(
    @SerializedName("results")
    val results: List<ResponseMovie>
)