package com.example.data.api.response

import com.example.model.SearchMovie

fun ResponseSearch.toModel(): SearchMovie {
    return SearchMovie(
        items = items?.toModel() ?: listOf(),
        totalCount = totalCount ?: 0
    )
}
