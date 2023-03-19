package com.example.nationalityapi.network

import com.example.nationalityapi.models.Countries
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("https://api.nationalize.io")
    suspend fun getNationality(
        @Query(PARAM_NAME) name: String
    ): Countries

    companion object {
        private const val PARAM_NAME = "name"
    }
}