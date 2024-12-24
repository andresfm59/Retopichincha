package com.example.retopichincha.data.api.network.response

import com.example.retopichincha.domain.model.RecipesModel
import com.google.gson.annotations.SerializedName

data class RecipesListResponse(
    @SerializedName("recipes")
    val recipes: List<RecipesResponse>
){
    fun toDomain(): RecipesListResponse {
        return RecipesListResponse(
            recipes = recipes
        )
    }
}
