package com.example.retopichincha.domain.repository

import com.example.retopichincha.domain.model.RecipesListModel

interface RecipesRepository {
    suspend fun getRecipes(): RecipesListModel?
}