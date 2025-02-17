package com.example.retopichincha.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.retopichincha.databinding.ItemFavoriteRecipeBinding
import com.example.retopichincha.domain.model.RecipesModel


class FavoriteAdapter(
    private val onFavoriteClick: (RecipesModel) -> Unit,
    private val onItemClick: (RecipesModel) -> Unit
) : RecyclerView.Adapter<FavoriteAdapter.RecipeFavoriteViewHolder>() {

    private var recipesList: List<RecipesModel> = emptyList()

    fun submitFavoriteList(favorites: List<RecipesModel>) {
        recipesList = favorites
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeFavoriteViewHolder {
        val binding =
            ItemFavoriteRecipeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecipeFavoriteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteAdapter.RecipeFavoriteViewHolder, position: Int) {
        val recipe =
            recipesList[position]
        holder.bind(recipe)
    }


    override fun getItemCount(): Int = recipesList.size

    inner class RecipeFavoriteViewHolder(private val binding: ItemFavoriteRecipeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(recipe: RecipesModel) {
            binding.recipe = recipe

            Glide.with(binding.root.context)
                .load(recipe.image)
                .into(binding.recipeImage)

            binding.favoriteButton.setOnClickListener {
                onFavoriteClick(recipe)
            }
            binding.root.setOnClickListener {
                onItemClick(recipe)
            }
        }
    }
}
