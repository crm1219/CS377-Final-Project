package com.example.art.data.model

// artwork list endpoint
data class ArtworkSearchResponse (
    val data: List<ArtworkDto>,
    val status: String
)
