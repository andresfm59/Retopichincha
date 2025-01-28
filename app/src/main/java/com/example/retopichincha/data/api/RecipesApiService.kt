package com.example.retopichincha.data.api

import com.example.retopichincha.data.api.network.response.RecipesListResponse
import retrofit2.http.GET

interface RecipesApiService {
        @GET("4afef549-6d4a-4992-94bd-aae4e4793d52")
        suspend fun getRecipes(): RecipesListResponse
}