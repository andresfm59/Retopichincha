package com.example.retopichincha.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.retopichincha.R
import com.example.retopichincha.databinding.ActivityOnboardingBinding
import com.example.retopichincha.presentation.item.OnboardingItem
import com.example.retopichincha.presentation.adapter.OnboardingAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnboardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnboardingBinding
    private val onboardingItems = listOf(
        OnboardingItem(R.drawable.ic_launcher_foreground, "Busca recetas", "Encuentra tus recetas favoritas."),
        OnboardingItem(R.drawable.ic_launcher_foreground, "Marca favoritas", "Guarda recetas en tu lista de favoritos."),
        OnboardingItem(R.drawable.ic_launcher_foreground, "Sigue los pasos", "Prepara recetas fácilmente.")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = OnboardingAdapter(onboardingItems)
        binding.viewpagerOnboarding.adapter = adapter

        binding.btnNext.setOnClickListener {
            val currentItem = binding.viewpagerOnboarding.currentItem
            if (currentItem < onboardingItems.size - 1) {
                binding.viewpagerOnboarding.currentItem = currentItem + 1
            } else {
                markOnboardingAsComplete()
                navigateToMainActivity()
            }
        }
    }

    private fun markOnboardingAsComplete() {
        val sharedPreferences = getSharedPreferences("app_preferences", Context.MODE_PRIVATE)
        sharedPreferences.edit().putBoolean("onboarding_complete", true).apply()
    }

    private fun navigateToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
