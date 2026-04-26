package com.example.art.data.model

// single item endpoint
data class ArtworkDetailResponse (
    val data: ArtworkDto,
    val config: ArtConfig?
)
