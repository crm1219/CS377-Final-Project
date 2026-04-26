package com.example.art.repository

import androidx.lifecycle.LiveData
import com.example.art.data.local.FavoriteArtworkDao
import com.example.art.data.mapper.toArtwork
import com.example.art.data.mapper.toEntity
import com.example.art.data.model.Artwork
import com.example.art.data.model.FavoriteArtworkEntity
import com.example.art.network.ArtApiService

// middle layer between the API, database, and view models
class ArtRepository(
    private val api: ArtApiService,
    private val dao: FavoriteArtworkDao
) {

    // get all favorite artworks from Room database
    fun getFavoriteArtworks(): LiveData<List<FavoriteArtworkEntity>> {
        return dao.getAllFavorites()
    }

    // load default list of artworks from API
    suspend fun getArtworks(): List<Artwork> {
        val response = api.getInitialArtworks()

        // combine APIT base iiifUrl with imageId
        val iiifUrl = response.config?.iiifUrl

        return response.data.map { artworkDto ->
            artworkDto.toArtwork(iiifUrl)
        }
    }

    // search artwork by keyword
    // if the user didn't type anything, just return default list
    suspend fun searchArtworks(query: String): List<Artwork> {
        if (query.isBlank()) {
            return getArtworks()
        }

        val response = api.searchArtworks(query)
        val iiifUrl = response.config?.iiifUrl

        return response.data.map { artworkDto ->
            artworkDto.toArtwork(iiifUrl)
        }
    }

    // get full details of one artwork by its ID
    suspend fun getArtworkDetails(artworkId: Int): Artwork {
        val response = api.getArtworkDetails(artworkId)
        val iiifUrl = response.config?.iiifUrl

        return response.data.toArtwork(iiifUrl)
    }

    // save an artwork as a favorite in Room
    suspend fun addFavorite(artwork: Artwork) {
        dao.insertFavorite(artwork.toEntity())
    }

    // remove an artwork from favorites by ID
    suspend fun removeFavorite(artworkId: Int) {
        dao.deleteFavoriteById(artworkId)
    }

    // check if an artwork is already saved as a favorite
    suspend fun isFavorite(artworkId: Int): Boolean {
        return dao.getFavoriteById(artworkId) != null
    }
}