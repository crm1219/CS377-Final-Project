package com.example.art.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private const val BASE_URL = "https://api.artic.edu/api/v1/"

    // Create the Retrofit instance
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // Expose the ArtApiService instance
    val apiService: ArtApiService by lazy {
        retrofit.create(ArtApiService::class.java)
    }
}
