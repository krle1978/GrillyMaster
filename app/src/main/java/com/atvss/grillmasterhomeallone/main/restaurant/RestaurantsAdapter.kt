package com.atvss.grillmasterhomeallone.main.restaurant

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.atvss.grillmasterhomeallone.R
import com.atvss.grillmasterhomeallone.databinding.ItemRestaurantBinding
import com.bumptech.glide.Glide

class RestaurantsAdapter(
    private val restaurantList:ArrayList<Restaurant>,
    private val onItemClick:(Restaurant) ->Unit
): RecyclerView.Adapter<RestaurantsAdapter.RestaurantViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        val binding = ItemRestaurantBinding.inflate(LayoutInflater.from(parent.context))
        return RestaurantViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return restaurantList.size
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        val restaurant = restaurantList[position]
        holder.bind(restaurant)
    }

    inner class RestaurantViewHolder(private val binding: ItemRestaurantBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(restaurant: Restaurant){
            binding.apply {
                tvName.text=restaurant.name
                tvPrice.text= "$${ restaurant.deliveryPrice }"
                tvGrade.text=restaurant.rating.toString()
                Glide.with(ivRestaurant.context).load(restaurant.logo.path).into(ivRestaurant)
                root.setOnClickListener {
                    onItemClick(restaurant)
                }
            }

        }
    }



}