package com.exampke.art.data.local

// imports
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.art.data.model.FavoriteArtworkEntity

// Data Access Object

// define database operations Room can perform
@Dao
interface FavoriteArtworkDao {

    // get all favorite artwork from the local database
    @Query("SELECT * FROM favorite_artworks ORDER BY title ASC")
    fun getAllFavorites(): LiveData<List<FavoriteArtworkEntity>>

    // get one favorite artwork by its ID
    @Query("SELECT * FROM favorite_artworks WHERE id = :artworkId LIMIT 1")
    suspend fun getFavoriteById(artworkId: Int): FavoriteArtworkEntity?

    // insert a new favorite artwork into the database
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(artwork: FavoriteArtworkEntity)

    // remove a favorite artwork from the database by ID
    @Query("DELETE FROM favorite_artworks WHERE id = :artworkId")
    suspend fun deleteFavoriteById(artworkId : Int)

}