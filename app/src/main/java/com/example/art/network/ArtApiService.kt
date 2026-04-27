package com.example.art.network

import com.example.art.data.model.ArtworkDetailResponse
import com.example.art.data.model.ArtworkSearchResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ArtApiService {
    // Load initial set of artworks into the app
    @GET("artworks")
    suspend fun getInitialArtworks(
        @Query("limit") limit: Int = 20,
        @Query("fields") fields: String = "id,title,image_id,artist_title,artist_display,date_display"
    ): ArtworkSearchResponse

    // Filter artworks by a given term
    @GET("artworks/search")
    suspend fun searchArtworks(
        @Query("q")searchTerm: String,
        @Query("fields") fields: String = "id,title,image_id,artist_title,artist_display,date_display"
    ): ArtworkSearchResponse

    // Get details about a specific artwork
    @GET("artworks/{id}")
    suspend fun getArtworkDetails(
        @Path("id") id: Int,
        @Query("fields") fields: String =
            "id,title,image_id,artist_title,artist_display,date_display,description,place_of_origin,medium_display"
    ): ArtworkDetailResponse
}
