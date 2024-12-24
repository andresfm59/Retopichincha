package com.example.retopichincha.data.respositoryImpl

import android.util.Log
import com.example.retopichincha.data.api.RecipesApiService
import com.example.retopichincha.domain.model.RecipesListModel
import com.example.retopichincha.domain.repository.RecipesRepository
import javax.inject.Inject

class RecipesRepositoryImpl @Inject constructor(private val apiService: RecipesApiService) :
    RecipesRepository {

    override suspend fun getRecipes(): RecipesListModel? {
        runCatching {
            apiService.getRecipes().recipes.map { it.toDomain() }
        }
            .onSuccess { recipesListModel ->
                Log.i("Compilado - Impl", "$recipesListModel")
            }
            .onFailure {
                Log.i("Compilado - Impl", "Ha ocurrido un error ${it.message}")
            }
        return null
    }
}