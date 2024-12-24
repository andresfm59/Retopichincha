package com.example.retopichincha.data.api.network.response

import com.example.retopichincha.domain.model.RecipesModel
import com.google.gson.annotations.SerializedName

data class RecipesResponse(

    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("ingredients")
    val ingredients: List<String>,
    @SerializedName("steps")
    val steps: List<String>
){
    fun toDomain(): RecipesModel {
        return RecipesModel(
            id =  id,
            name = name,
            image = image,
            description = description,
            ingredients = ingredients,
            steps  = steps
        )
    }
}
