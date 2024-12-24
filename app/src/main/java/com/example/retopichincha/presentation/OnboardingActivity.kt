package com.example.retopichincha.presentation

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.retopichincha.R
import com.example.retopichincha.databinding.ActivityOnboardingBinding
import com.example.retopichincha.presentation.item.OnboardingItem
import com.example.retopichincha.presentation.adapter.OnboardingAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.log

@AndroidEntryPoint
class OnboardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnboardingBinding
    private val onboardingItems = listOf(
        OnboardingItem(
            R.drawable.search_recipes,
            "Busca recetas",
            "Encuentra recetas de todo tipo."
        ),
        OnboardingItem(
            R.drawable.favorite_recipes,
            "Marca las que más te gusten",
            "Guardar tus recetas en tu lista de favoritos."
        ),
        OnboardingItem(R.drawable.step_by_step, "Y Disfruta!", "Prepara recetas fácilmente.")
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.decorView.systemUiVisibility =
            (View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
        window.statusBarColor = Color.TRANSPARENT

        val adapter = OnboardingAdapter(onboardingItems)
        binding.viewpagerOnboarding.adapter = adapter
        binding.viewpagerOnboarding.offscreenPageLimit = onboardingItems.size
        Log.i("Compilado", "Recetas obtenidas: $onboardingItems.size")

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
