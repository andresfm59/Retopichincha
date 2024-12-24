package com.example.retopichincha.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retopichincha.R
import com.example.retopichincha.databinding.FragmentRecipesBinding
import com.example.retopichincha.presentation.adapter.FavoriteAdapter
import com.example.retopichincha.presentation.adapter.RecipeAdapter
import com.example.retopichincha.presentation.state.RecipesListState
import com.example.retopichincha.presentation.viewmodel.RecipesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipesListFragment : Fragment() {

    private var _binding: FragmentRecipesBinding? = null
    private val binding get() = _binding!!

    private val viewModel: RecipesViewModel by viewModels()

    private lateinit var recipeAdapter: RecipeAdapter
    private lateinit var favoriteAdapter: FavoriteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentRecipesBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        observeViewModel()
    }

    private fun setupRecyclerView() {
        recipeAdapter = RecipeAdapter(
            onFavoriteClick = { recipe ->
                viewModel.toggleFavorite(recipe)
            },
            onItemClick = { recipe ->
                val fragment = RecipeDetailFragment()
                fragment.arguments = Bundle().apply {
                    putParcelable("recipe", recipe)
                }

                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .addToBackStack(null)
                    .commit()
            }
        )
        favoriteAdapter = FavoriteAdapter(
            onFavoriteClick = { recipe ->
                viewModel.toggleFavorite(recipe)
            },
            onItemClick = { recipe ->
                val fragment = RecipeDetailFragment()
                fragment.arguments = Bundle().apply {
                    putParcelable("recipe", recipe)
                }

                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .addToBackStack(null)
                    .commit()
            }
        )

        binding.favoriteRecipesRecyclerView.apply {
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.HORIZONTAL, // Cambiar a diseño horizontal
                false
            )
            adapter = favoriteAdapter
        }

        binding.recipesRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = recipeAdapter
        }

        binding.favoriteRecipesRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = favoriteAdapter
        }
    }

    private fun observeViewModel() {
        viewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                is RecipesListState.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }

                is RecipesListState.Success -> {
                    binding.progressBar.visibility = View.GONE
                    binding.RecipesSectionLayout.visibility = View.VISIBLE
                    recipeAdapter.submitList(state.recipes)
                }

                is RecipesListState.Error -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(requireContext(), state.message, Toast.LENGTH_LONG).show()
                }
            }
        }

        viewModel.favoriteRecipes.observe(viewLifecycleOwner) { favorites ->
            if (favorites.isNotEmpty()) {
                binding.favoriteSectionLayout.visibility =
                    View.VISIBLE
                favoriteAdapter.submitFavoriteList(favorites)
            } else {
                binding.favoriteSectionLayout.visibility =
                    View.GONE
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
