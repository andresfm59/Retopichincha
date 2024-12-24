package com.example.retopichincha.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retopichincha.domain.model.RecipesListModel
import com.example.retopichincha.domain.model.RecipesModel
import com.example.retopichincha.domain.usecase.GetRecipesUseCase
import com.example.retopichincha.presentation.state.RecipesListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class RecipesViewModel @Inject constructor(
    private val getRecipesUseCase: GetRecipesUseCase,
) : ViewModel() {

    private val _state = MutableLiveData<RecipesListState>()
    val state: LiveData<RecipesListState> = _state

    private val _recipes = MutableLiveData<RecipesListModel>()
    val recipes: LiveData<RecipesListModel> = _recipes

    private val _favoriteRecipes = MutableLiveData<MutableList<RecipesListModel>>(mutableListOf())
    val favoriteRecipes: MutableLiveData<MutableList<RecipesListModel>> = _favoriteRecipes

    init {
        getRecipes()
    }

    fun getRecipes() {
        viewModelScope.launch {
            _state.value = RecipesListState.Loading
            val result = withContext(Dispatchers.IO) { getRecipesUseCase() }
            Log.i("Compilado - ViewModel", "$result")

            if (result != null) {
                _state.value = RecipesListState.Success(result.recipes)
            } else {
                _state.value = RecipesListState.Error("Ha ocurrido un error, intentelo más tarde $result")
            }
        }
    }

    fun toggleFavorite(recipe: RecipesModel) {
        _favoriteRecipes.value = _favoriteRecipes.value?.apply {
        //    if (contains(recipe)) {
           //     remove(recipe)
          //  } else {
           //     add(recipe)
         //   }
        }
    }
}
