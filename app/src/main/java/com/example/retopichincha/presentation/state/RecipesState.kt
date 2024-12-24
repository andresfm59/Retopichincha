package com.example.retopichincha.presentation.state

import com.example.retopichincha.domain.model.RecipesModel

sealed class RecipesListState {
    object Loading : RecipesListState()
    data class Success(val recipes: List<RecipesModel>) : RecipesListState()
    data class Error(val message: String) : RecipesListState()
}