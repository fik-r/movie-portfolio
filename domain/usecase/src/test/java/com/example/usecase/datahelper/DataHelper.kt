package com.example.usecase.datahelper

import com.example.model.Movie


object DataHelper {
    val dataMovies: List<Movie> =
        listOf(
            Movie(
                "1",
                "Lorem Ipsum",
                "2002-08-09",
                "https://picsum.photos/200",
                "https://picsum.photos/200",
            )
        )
    val dataMovie: Movie = Movie(
        "1",
        "Lorem Ipsum",
        "2002-08-09",
        "https://picsum.photos/200",
        "https://picsum.photos/200",
    )
}