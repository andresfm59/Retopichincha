package com.example.retopichincha.presentation.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.retopichincha.databinding.ItemRecipeBinding
import com.example.retopichincha.domain.model.RecipesModel

class RecipeAdapter(
    private val onFavoriteClick: (RecipesModel) -> Unit
) : ListAdapter<RecipesModel, RecipeAdapter.RecipeViewHolder>(RecipeDiffCallback()) {

    // ViewHolder que usa Data Binding
    inner class RecipeViewHolder(private val binding: ItemRecipeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(recipe: RecipesModel) {
            binding.recipe = recipe // Vinculamos el modelo con el layout

            // Usamos Glide para cargar la imagen
            Glide.with(binding.recipeImage.context)
                .load(recipe.image)
                .into(binding.recipeImage)

            // Configurar el evento de clic en el botón de favoritos
            binding.favoriteButton.setOnClickListener {
                onFavoriteClick(recipe)
            }

            // Forzamos la ejecución de Data Binding
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val binding = ItemRecipeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecipeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    // Callback para calcular diferencias en la lista
    class RecipeDiffCallback : DiffUtil.ItemCallback<RecipesModel>() {
        override fun areItemsTheSame(oldItem: RecipesModel, newItem: RecipesModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: RecipesModel, newItem: RecipesModel): Boolean {
            return oldItem == newItem
        }
    }
}
