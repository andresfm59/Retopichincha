package com.example.retopichincha.domain.usecase

import com.example.retopichincha.domain.model.RecipesListModel
import com.example.retopichincha.domain.repository.RecipesRepository
import javax.inject.Inject

open class GetRecipesUseCase @Inject constructor(
    private val repository: RecipesRepository
) {
    suspend operator fun invoke(): RecipesListModel? {
        return repository.getRecipes()
    }
}