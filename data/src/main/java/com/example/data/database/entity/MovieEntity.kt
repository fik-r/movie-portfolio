package com.example.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.model.Movie

@Entity(tableName = "movie")
data class MovieEntity(
    @PrimaryKey
    val id: String = "",
    val backgroundImage: String = "",
    val name: String = "",
    val rating: String = "",
    val released: String = "",
)

fun MovieEntity?.mapToModel(): Movie {
    return if (this != null)
        Movie(
            id, name, released, rating, backgroundImage.orEmpty()
        )
    else Movie()
}

fun Movie.mapToEntity(): MovieEntity {
    return MovieEntity(
        id, backgroundImage, name, rating, released
    )
}