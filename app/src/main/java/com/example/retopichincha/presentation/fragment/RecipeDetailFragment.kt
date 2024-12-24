package com.example.retopichincha.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.retopichincha.databinding.FragmentRecipeDetailBinding
import com.example.retopichincha.domain.model.RecipesModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipeDetailFragment : Fragment() {

    private var _binding: FragmentRecipeDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var recipe: RecipesModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecipeDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getParcelable<RecipesModel>("recipe")?.let {
            recipe = it
            displayRecipeDetails()
        }
    }

    private fun displayRecipeDetails() {
        binding.apply {
            tvRecipeName.text = recipe.name
            ivRecipeDescription.text = recipe.description
            tvIngredients.text = recipe.ingredients.joinToString("\n")
            tvSteps.text = recipe.steps.joinToString("\n")

            Glide.with(this@RecipeDetailFragment)
                .load(recipe.image)
                .into(ivRecipeImage)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
