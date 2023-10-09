package com.example.data.api.response

import com.example.model.Movie

fun ResponseMovie.toModel(): Movie {
    return Movie(
        id.orEmpty(),
        title.orEmpty(),
        released.orEmpty(),
        popularity.orEmpty(),
        backgroundImage.orEmpty(),
    )
}

fun List<ResponseMovie>.toModel(): List<Movie> {
    val list = mutableListOf<Movie>()
    this.forEach {
        list.add(it.toModel())
    }
    return list
}