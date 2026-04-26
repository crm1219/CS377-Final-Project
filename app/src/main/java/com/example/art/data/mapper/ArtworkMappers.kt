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
        title = title ?: "Untitled",
        imageId = buildImageURL(iiifUrl, imageId),
        artistTitle = artistTitle,
        artistDisplay = artistDisplay,
        dateDisplay = dateDisplay,
        description = description,
        placeOfOrigin = placeOfOrigin,
        mediumDisplay = mediumDisplay
    )
}

// takes app model and converts it into a Room database object
fun Artwork.toEntity(): FavoriteArtworkEntity {
    return FavoriteArtworkEntity(
        id = id,
        title = title,
        imageId = imageId,
        artistTitle = artistTitle,
        artistDisplay = artistDisplay,
        dateDisplay = dateDisplay,
        description = description,
        placeOfOrigin = placeOfOrigin,
        mediumDisplay = mediumDisplay
    )
}

// takes saved Room object and converts it back to regular app model
fun FavoriteArtworkEntity.toArtwork(): Artwork {
    return Artwork(
        id = id,
        title = title,
        imageId = imageId,
        artistTitle = artistTitle,
        artistDisplay = artistDisplay,
        dateDisplay = dateDisplay,
        description = description,
        placeOfOrigin = placeOfOrigin,
        mediumDisplay = mediumDisplay
    )
}
