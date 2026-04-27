package com.example.art.data.model

// imports
import com.google.gson.annotations.SerializedName

// Data Transfer Object

// matches raw artwork JSON fields from API
data class ArtworkDto(
    val id: Int,
    val title: String?,

    @SerializedName("image_id")
    val imageId: String?,

    @SerializedName("artist_title")
    val artistTitle: String?,

    @SerializedName("artist_display")
    val artistDisplay: String?,

    @SerializedName("date_display")
    val dateDisplay: String?,

    val description: String?,

    @SerializedName("place_of_origin")
    val placeOfOrigin: String?,

    @SerializedName("medium_display")
    val mediumDisplay: String?

)