package com.example.data.api.response

import com.google.gson.annotations.SerializedName

data class ResponseMovie(
    @SerializedName("id")
    val id: String? = "",
    @SerializedName("title")
    val title: String? = "",
    @SerializedName("backdrop_path")
    val backgroundImage: String? = "",
    @SerializedName("popularity")
    val popularity: String? = "",
    @SerializedName("release_date")
    val released: String? = ""
)