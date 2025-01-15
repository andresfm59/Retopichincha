package com.example.retopichincha.data.api

import com.example.retopichincha.data.api.network.response.RecipesListResponse
import retrofit2.http.GET

interface RecipesApiService {
        @GET("c0643a83-c948-485f-a3d4-fb400b44cb5b")
        suspend fun getRecipes(): RecipesListResponse
}