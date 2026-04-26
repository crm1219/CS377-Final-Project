package com.example.art.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

// Room database
@Entity(tableName = "favorite_artworks")
data class FavoriteArtworkEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val imageId: String,
    val artistTitle: String,
    val artistDisplay: String,
    val dateDisplay: String,
    val description: String,
    val placeOfOrigin: String,
    val mediumDisplay: String
)