package com.example.art.data.mapper

// imports
import com.example.art.data.model.Artwork
import com.example.art.data.model.ArtworkDto
import com.example.art.data.model.FavoriteArtworkEntity

// function to build image
private fun buildImageURL(iiifUrl: String?, imageId: String?): String? {
    if (iiifUrl.isNullOrBlank() || imageId.isNullOrBlank()) return null
    return "${iiifUrl.trimEnd('/')}/$imageId/full/843,/0/default.jpg"
}

// takes raw API data and turns it into clean Artwork object
fun ArtworkDto.toArtwork(iiifUrl: String?): Artwork {
    return Artwork(
        id = id,
        title = title ?: "Unknown title.",
        imageId = buildImageURL(iiifUrl, imageId) ?: "",
        artistTitle = artistTitle ?: "Unknown artist.",
        artistDisplay = artistDisplay ?: "Unknown artist.",
        dateDisplay = dateDisplay ?: "Unknown date.",
        description = description ?: "No description available.",
        placeOfOrigin = placeOfOrigin ?: "Unknown origin.",
        mediumDisplay = mediumDisplay ?: "Unknown medium.",
    )
}

// takes app model and converts it into a Room database object
fun Artwork.toEntity(): FavoriteArtworkEntity {
    return FavoriteArtworkEntity(
        id = id,
        title = title,
        image_id = imageId,
        artist_title = artistTitle,
        artist_display = artistDisplay,
        date_display = dateDisplay,
        description = description,
        place_of_origin = placeOfOrigin,
        medium_display = mediumDisplay
    )
}

// takes saved Room object and converts it back to regular app model
fun FavoriteArtworkEntity.toArtwork(): Artwork {
    return Artwork(
        id = id,
        title = title,
        imageId = image_id,
        artistTitle = artist_title,
        artistDisplay = artist_display,
        dateDisplay = date_display,
        description = description,
        placeOfOrigin = place_of_origin,
        mediumDisplay = medium_display
    )
}
