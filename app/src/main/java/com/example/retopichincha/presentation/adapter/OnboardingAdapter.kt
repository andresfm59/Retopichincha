package com.example.retopichincha.presentation.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.retopichincha.databinding.FragmentOnboardingPageBinding
import com.example.retopichincha.presentation.item.OnboardingItem

class OnboardingAdapter(
    private val items: List<OnboardingItem>
) : RecyclerView.Adapter<OnboardingAdapter.OnboardingViewHolder>() {

    inner class OnboardingViewHolder(private val binding: FragmentOnboardingPageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: OnboardingItem) {
            Log.i("OnboardingAdapter", "Binding item: ${item.title}, imageRes: ${item.imageRes}")
            binding.imageOnboarding.setImageResource(item.imageRes)
            binding.titleOnboarding.text = item.title
            binding.descriptionOnboarding.text = item.description
        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnboardingViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = FragmentOnboardingPageBinding.inflate(inflater, parent, false)
        return OnboardingViewHolder(binding)
    }


    override fun onBindViewHolder(holder: OnboardingViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}
