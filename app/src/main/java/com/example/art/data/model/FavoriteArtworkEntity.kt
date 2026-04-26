package com.example.art.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

// Room database
@Entity(tablename = "favorite_artworks")
data class FavoriteArtworkEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val image_id: String,
    val artist_display: String,
    val date_display: String,
    val description: String,
    val place_of_origin: String,
    val medium_display: String
)