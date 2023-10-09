package com.example.common

sealed class Result<T: Any> {
    data class Success<T: Any>(
        val data: T
    ) : Result<T>()

    data class Error<T: Any>(
        val cause: String
    ) : Result<T>()
}