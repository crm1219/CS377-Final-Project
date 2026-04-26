package com.example.art.network

import com.example.art.data.model.ArtworkDetailResponse
import com.example.art.data.model.ArtworkSearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ArtApiService {
    // Load initial set of artworks into the app
    @GET("artworks/artworks?limit=20&fields=id,title,image_id,artist_display,date_display")
    suspend fun getInitialArtworks(): ArtworkSearchResponse

    // Filter artworks by a given term
    @GET("artworks/search?fields=id,title,image_id,artist_display,date_display")
    suspend fun searchArtworks(@Query("q")searchTerm: String) : ArtworkSearchResponse

    // Get details about a specific artwork
    @GET("artworks/?fields=id,title,image_id,artist_display,date_display,description,place_of_origin,medium_display")
    suspend fun getArtworkDetails(@Query("ids")id: Int) : ArtworkDetailResponse
}
