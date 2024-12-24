package com.example.retopichincha.presentation.helper

import android.content.Context
import android.content.SharedPreferences
import com.example.retopichincha.domain.model.RecipesModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SharedPreferencesHelper @Inject constructor(@ApplicationContext private val context: Context) {
    private val sharedPreferences = context.getSharedPreferences("my_preferences", Context.MODE_PRIVATE)


    fun saveFavoriteRecipe(recipe: RecipesModel) {
        val recipes = getFavoriteRecipes().toMutableList()
        if (!recipes.contains(recipe)) {
            recipes.add(recipe)
        }

        val json = Gson().toJson(recipes)
        sharedPreferences.edit().putString("favorite_recipes", json).apply()
    }

    fun removeFavoriteRecipe(recipe: RecipesModel) {
        val recipes = getFavoriteRecipes().toMutableList()
        recipes.remove(recipe)

        val json = Gson().toJson(recipes)
        sharedPreferences.edit().putString("favorite_recipes", json).apply()
    }

    fun getFavoriteRecipes(): List<RecipesModel> {
        val json = sharedPreferences.getString("favorite_recipes", "[]")
        val type = object : TypeToken<List<RecipesModel>>() {}.type
        return Gson().fromJson(json, type)
    }

    fun isRecipeFavorite(id: Int): Boolean {
        val recipes = getFavoriteRecipes()
        return recipes.any { it.id == id }
    }
}
