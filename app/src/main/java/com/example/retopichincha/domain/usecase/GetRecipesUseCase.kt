package com.example.retopichincha.domain.usecase

import com.example.retopichincha.domain.repository.RecipesRepository
import javax.inject.Inject

class GetRecipesUseCase @Inject constructor(private val repository: RecipesRepository) {
    suspend operator fun invoke() = repository.getRecipes()

}