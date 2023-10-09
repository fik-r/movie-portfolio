package com.example.data.api.service

import com.example.data.api.response.ResponseMovie
import com.example.data.api.response.ResponseListMovie
import com.example.data.api.response.ResponseSearch
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {
    @GET("movie/popular")
    suspend fun getMovies(@Query("page") page: Int = 1): ResponseListMovie

    @GET("movie/{movie_id}")
    suspend fun getMovie(
        @Path("movie_id") id: String,
    ): ResponseMovie

    @GET("search/movie")
    suspend fun searchMovies(
        @Query("query") search: String,
        @Query("page") page: Int,
    ): ResponseSearch
}