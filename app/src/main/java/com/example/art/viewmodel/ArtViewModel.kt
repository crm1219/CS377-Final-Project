package com.example.art.viewmodel

// imports
import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.art.data.local.ArtDatabase
import com.example.art.data.model.Artwork
import com.example.art.network.RetrofitInstance
import com.example.art.repository.ArtRepository
import kotlinx.coroutines.launch

// ViewModel for main artwork list screen
class ArtViewModel(application: Application): AndroidViewModel(application) {

    private val repository = ArtRepository(
        api = RetrofitInstance.apiService,
        dao = ArtDatabase.getDatabase(application).favoriteArtworkDao()
    )

    // holds the list of artworks shown in the RecycleView
    private val _artworks = MutableLiveData<List<Artwork>>()
    val artworks: LiveData<List<Artwork>> = _artworks

    // loading state for progress bar
    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    // error message shown in the Fragment
    private val _error = MutableLiveData<String?>(null)
    val error: LiveData<String?> = _error

    init {
        loadArtworks()
    }

    // load the default set of artworks when app is first opened
    fun loadArtworks() {
        viewModelScope.launch {
            _loading.value = true
            _error.value = null

            try {
                val result = repository.getArtworks()
                _artworks.value = result

                if (result.isEmpty()) {
                    _error.value = "No artworks found."
                }
            } catch (e: Exception) {
                Log.e("ArtViewModel", "Error loading artworks", e)
                _error.value = "Unable to load artworks: ${e.message}"
            } finally {
                _loading.value = false
            }
        }
    }

    // search artworks by keyword
    fun searchArtworks(query: String) {
        viewModelScope.launch {
            _loading.value = true
            _error.value = null

            try {
                val result = repository.searchArtworks(query)
                _artworks.value = result

                if (result.isEmpty()) {
                    _error.value = "No artworks found."
                }
            } catch (e: Exception) {
                Log.e("ArtViewModel", "Error searching artworks", e)
                _error.value = "Unable to load artworks: ${e.message}"
            } finally {
                _loading.value = false
            }
        }
    }
}