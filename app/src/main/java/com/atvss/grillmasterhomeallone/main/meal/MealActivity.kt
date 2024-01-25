package com.atvss.grillmasterhomeallone.main.meal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.GridLayout
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.atvss.grillmasterhomeallone.R
import com.atvss.grillmasterhomeallone.api.Api
import com.atvss.grillmasterhomeallone.api.RetrofitClient
import com.atvss.grillmasterhomeallone.databinding.ActivityMealBinding
import com.atvss.grillymaster.main.meal.MealAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MealActivity : AppCompatActivity() {

    lateinit var binding: ActivityMealBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMealBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbarMeal)

        supportActionBar?.title=intent.getStringExtra("name")
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.icon_back)

        val manager = GridLayoutManager(this,2)
        binding.rvMeals.layoutManager = manager

        val id = intent.getIntExtra("id",-1)
        getMeals(id)
    }
    private  fun getMeals(id: Int){
        RetrofitClient.getClient().create(Api::class.java).getMeals(id).enqueue(object : Callback<MealData>{
            override fun onResponse(call: Call<MealData>, response: Response<MealData>) {
                if (response.body() == null){
                    Log.d("ATVSS123", "onResponse: IS null")
                    return
                }
                if (!response.isSuccessful){
                    Log.d("ATVSS123", "onResponse: IS NOT Successful")
                    return
                }
                val meals : ArrayList<Meal> = ArrayList()
                meals.addAll(response.body()!!.data)
                setMealAdapter(meals)
                Log.d("ATVSS123", "onResponse: ${meals.size}")
            }

            override fun onFailure(call: Call<MealData>, t: Throwable) {
                Toast.makeText(this@MealActivity, t.localizedMessage, Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun setMealAdapter(meals:ArrayList<Meal>){
        val adapter = MealAdapter(meals)
        binding.rvMeals.adapter = adapter
    }
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}