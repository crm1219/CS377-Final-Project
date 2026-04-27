package com.example.art.data.model

import com.google.gson.annotations.SerializedName

// needed to build image URL from API config object
data class ArtConfig(
    @SerializedName("iiif_url")
    val iiifUrl: String
)