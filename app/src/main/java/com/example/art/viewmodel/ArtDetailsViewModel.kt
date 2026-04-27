package com.example.art.viewmodel

// imports
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.art.data.local.ArtDatabase
import com.example.art.data.model.Artwork
import com.example.art.network.RetrofitInstance
import com.example.art.repository.ArtRepository
import kotlinx.coroutines.launch

// ViewModel for artwork details screen
class ArtDetailsViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = ArtRepository(
        api = RetrofitInstance.apiService,
        dao = ArtDatabase.getDatabase(application).favoriteArtworkDao()
    )

    // holds selected artwork details
    private val _artwork = MutableLiveData<Artwork?>()
    val artwork: LiveData<Artwork?> = _artwork

    // tracks is artwork is a favorite
    private val _isFavorite = MutableLiveData(false)
    val isFavorite: LiveData<Boolean> = _isFavorite

    // loading state
    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    // error message
    private val _error = MutableLiveData<String?>(null)
    val error: LiveData<String?> = _error

    // load one artwork using ID
    fun loadArtwork(artworkId: Int) {
        viewModelScope.launch {
            _loading.value = true
            _error.value = null

            try {
                val loadedArtwork = repository.getArtworkDetails(artworkId)
                _artwork.value = loadedArtwork
                _isFavorite.value = repository.isFavorite(artworkId)
            } catch (e: Exception) {
                _error.value = "Unable to load artwork details: ${e.message}"
            } finally {
                _loading.value = false
            }
        }
    }

    // add/remove artwork from favorites
    fun toggleFavorite() {
        val currentArtwork = _artwork.value ?: return

        viewModelScope.launch {
            try {
                if (_isFavorite.value == true) {
                    repository.removeFavorite(currentArtwork.id)
                    _isFavorite.value = false
                } else {
                    repository.addFavorite(currentArtwork)
                    _isFavorite.value = true
                }
            } catch (e: Exception) {
                _error.value = "Unable to update favorites: ${e.message}"
            }
        }
    }
}