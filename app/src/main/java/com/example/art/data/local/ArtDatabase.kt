package com.example.art.data.local

// imports
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.art.data.local.FavoriteArtworkDao
import com.example.art.data.model.FavoriteArtworkEntity

// main Room database class for the app
@Database(
    entities = [FavoriteArtworkEntity::class],
    version = 1,
    exportSchema = false
)
abstract class ArtDatabase : RoomDatabase() {

    // gives access to the favorite artwork DAO
    abstract fun favoriteArtworkDao(): FavoriteArtworkDao

    companion object {

        // makes sure all threads see the latest value of INSTANCE
        @Volatile
        private var INSTANCE: ArtDatabase? = null

        // return the single database instance for the whole app
        fun getDatabase(context: Context): ArtDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ArtDatabase::class.java,
                    "art_database"
                ).build()

                INSTANCE = instance
                instance
            }
        }
    }
}