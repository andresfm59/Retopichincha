package com.example.retopichincha.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RecipesModel(
    val id: Int,
    val name: String,
    val image: String,
    val description: String,
    val ingredients: List<String>,
    val steps: List<String>,
    var isFavorite: Boolean = false
): Parcelable