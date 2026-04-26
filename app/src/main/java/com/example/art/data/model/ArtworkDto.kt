package com.example.art.data.model

// Data Transfer Object

// matches raw artwork JSON fields from API
data class ArtworkDto(
    val id: Int,
    val title: String,
    val imageId: String,
    val artistTitle: String,
    val artistDisplay: String,
    val dateDisplay: String,
    val description: String,
    val placeOfOrigin: String,
    val mediumDisplay: String

)