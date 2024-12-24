package com.example.retopichincha.data.respositoryImpl

import android.util.Log
import com.example.retopichincha.data.api.RecipesApiService
import com.example.retopichincha.domain.model.RecipesListModel
import com.example.retopichincha.domain.repository.RecipesRepository
import javax.inject.Inject

class RecipesRepositoryImpl @Inject constructor(private val apiService: RecipesApiService) :
    RecipesRepository {

    override suspend fun getRecipes(): RecipesListModel? {
        return runCatching {
            val recipes = apiService.getRecipes().recipes.map { it.toDomain() }
            RecipesListModel(recipes) // Asegúrate de empaquetarlo en un RecipesListModel
        }
            .onSuccess { recipesListModel ->
                Log.i("Compilado - Impl", "Recetas obtenidas: $recipesListModel")
            }
            .onFailure {
                Log.i("Compilado - Impl", "Ha ocurrido un error: ${it.message}")
            }
            .getOrNull()
    }
}