package com.example.data.datahelper

import com.example.common.Result
import com.example.model.Movie
import com.example.model.SearchMovie

object DataHelper {
    val dataMovies: List<Movie> =
        listOf(
            Movie(
                "1",
                "Lorem Ipsum",
                "2022-03-03",
                "4.2",
                "https://picsum.photos/200",
            )
        )
    val dataMovie: Movie = Movie(
        "1",
        "Lorem Ipsum",
        "2022-03-03",
        "4.2",
        "https://picsum.photos/200",
    )
    val dataSearchGames: SearchMovie =
        SearchMovie(totalCount = 1, items = dataMovies)

    val gamesRemoteData: Result<List<Movie>> =
        Result.Success(dataMovies)
    val movieRemoteData: Result<Movie> =
        Result.Success(dataMovie)
    val searchGamesRemoteData: Result<SearchMovie> =
        Result.Success(dataSearchGames)
}