package com.atvss.grillymaster.main.meal

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.atvss.grillmasterhomeallone.databinding.ItemMealBinding
import com.atvss.grillmasterhomeallone.main.meal.Meal

import com.bumptech.glide.Glide

class MealAdapter(
    private val meals: ArrayList<Meal>
) : RecyclerView.Adapter<MealAdapter.MealViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        val binding = ItemMealBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MealViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return meals.size
    }

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        val meal = meals[position]
        holder.bind(meal)
    }

    inner class MealViewHolder(val binding: ItemMealBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(meal: Meal) {
            binding.apply {
                tvName.text = meal.name
                tvPrice.text = "$${meal.price}"
                Glide.with(itemView.context).load(meal.picture.path).into(ivMeal)
            }
        }
    }
}