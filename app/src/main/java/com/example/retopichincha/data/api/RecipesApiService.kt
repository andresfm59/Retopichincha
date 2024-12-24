package com.example.retopichincha.data.api

import com.example.retopichincha.data.api.network.response.RecipesListResponse
import retrofit2.http.GET

interface RecipesApiService {
        @GET("/recetas")
        suspend fun getRecipes(): RecipesListResponse
}