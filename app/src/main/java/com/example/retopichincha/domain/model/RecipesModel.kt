package com.example.retopichincha.domain.model

data class RecipesModel(
    val id: Int,
    val name: String,
    val image: String,
    val description: String,
    val ingredients: List<String>,
    val steps: List<String>
)