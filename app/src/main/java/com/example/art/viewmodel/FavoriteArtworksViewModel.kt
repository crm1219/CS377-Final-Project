package com.example.art.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.art.data.local.ArtDatabase
import com.example.art.data.model.FavoriteArtworkEntity

// ViewModel for favorites screen
class FavoriteArtworksViewModel(application: Application) : AndroidViewModel(application) {

    val favorites: LiveData<List<FavoriteArtworkEntity>> =
        ArtDatabase.getDatabase(application)
            .favoriteArtworkDao()
            .getAllFavorites()
}