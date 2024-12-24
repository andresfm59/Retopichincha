package com.example.retopichincha.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retopichincha.domain.model.RecipesModel
import com.example.retopichincha.domain.usecase.GetRecipesUseCase
import com.example.retopichincha.presentation.helper.SharedPreferencesHelper
import com.example.retopichincha.presentation.state.RecipesListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class RecipesViewModel @Inject constructor(
    private val getRecipesUseCase: GetRecipesUseCase,
    private val sharedPreferencesHelper: SharedPreferencesHelper
) : ViewModel() {

    private val _state = MutableLiveData<RecipesListState>()
    val state: LiveData<RecipesListState> = _state

    private val _favoriteRecipes = MutableLiveData<List<RecipesModel>>(emptyList())
    val favoriteRecipes: LiveData<List<RecipesModel>> = _favoriteRecipes

    init {
        getRecipes()
        loadFavoriteRecipes()
    }

    fun getRecipes() {
        viewModelScope.launch {
            _state.value = RecipesListState.Loading
            val result = withContext(Dispatchers.IO) { getRecipesUseCase() }

            if (result != null) {
                _state.value = RecipesListState.Success(result.recipes)
            } else {
                _state.value = RecipesListState.Error("Error al obtener recetas")
            }
        }
    }

    private fun loadFavoriteRecipes() {
        _favoriteRecipes.value = sharedPreferencesHelper.getFavoriteRecipes()
    }

    fun toggleFavorite(recipe: RecipesModel) {
        if (sharedPreferencesHelper.isRecipeFavorite(recipe.id)) {
            sharedPreferencesHelper.removeFavoriteRecipe(recipe)
        } else {
            sharedPreferencesHelper.saveFavoriteRecipe(recipe)
        }
        loadFavoriteRecipes()
    }
}
