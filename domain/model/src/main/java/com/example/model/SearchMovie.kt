package com.example.model

data class SearchMovie(
    val items: List<Movie> = listOf(),
    val totalCount: Int = 0
)