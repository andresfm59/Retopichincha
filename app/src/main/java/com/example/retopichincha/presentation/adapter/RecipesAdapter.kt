package com.example.retopichincha.presentation.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.retopichincha.databinding.ItemRecipeBinding
import com.example.retopichincha.domain.model.RecipesModel

class RecipeAdapter(
    private val onFavoriteClick: (RecipesModel) -> Unit,
    private val onItemClick: (RecipesModel) -> Unit
) : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    private var recipesList: List<RecipesModel> = emptyList()

    fun submitList(recipes: List<RecipesModel>) {
        recipesList = recipes
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val binding = ItemRecipeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecipeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe =
            recipesList[position]
        holder.bind(recipe)
    }

    override fun getItemCount(): Int = recipesList.size

    inner class RecipeViewHolder(private val binding: ItemRecipeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(recipe: RecipesModel) {
            binding.recipe = recipe

            Glide.with(binding.root.context)
                .load(recipe.image) // Aquí usas la URL de la imagen que tienes en tu modelo
                .into(binding.recipeImage) // Aquí va el ImageView en el layout

            binding.favoriteButton.setOnClickListener {
                onFavoriteClick(recipe) // Llamada para marcar o desmarcar como favorito
            }
            binding.root.setOnClickListener {
                onItemClick(recipe) // Llamada para navegar a los detalles de la receta
            }
        }
    }
}
