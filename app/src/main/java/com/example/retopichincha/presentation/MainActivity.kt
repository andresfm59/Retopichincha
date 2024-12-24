package com.example.retopichincha.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.retopichincha.R
import com.example.retopichincha.presentation.fragment.RecipesListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, RecipesListFragment())
                .commit()
        }
    }
}
