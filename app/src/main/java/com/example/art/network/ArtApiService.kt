package com.example.art.network

import com.example.art.data.model.ArtworkSearchResponse
import retrofit2.http.GET

interface ArtApiService {
    @GET("artworks/search?q=sky&fields=title,image_id,artist_display,date_display")
    suspend fun getInitialArtworks(): ArtworkSearchResponse
}
