package com.atvss.grillmasterhomeallone.main.restaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.atvss.grillmasterhomeallone.R
import com.atvss.grillmasterhomeallone.api.Api
import com.atvss.grillmasterhomeallone.api.RetrofitClient
import com.atvss.grillmasterhomeallone.databinding.ActivityMainBinding
import com.atvss.grillmasterhomeallone.main.meal.MealActivity
import com.atvss.grillmasterhomeallone.startActivityAuth
import com.atvss.grillmasterhomeallone.startActivityMain
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbarMain)
        supportActionBar?.setDisplayShowCustomEnabled(false)
        supportActionBar?.setIcon(R.drawable.logo)
        val manager = LinearLayoutManager(this,RecyclerView.VERTICAL,false)
        binding.rvRestaurants.layoutManager=manager

        getRestaurantList()
    }
    private fun getRestaurantList(){
        RetrofitClient.getClient().create(Api::class.java)
            .getRestaurants()
            .enqueue(object : Callback<RestaurantData>{
            override fun onResponse(
                call: Call<RestaurantData>,
                response: Response<RestaurantData>
            ) {
                val restaurantList : ArrayList<Restaurant> = ArrayList()
                if (response.body() == null){
                    return
                }
                if (!response.isSuccessful){
                    return
                }
                val restaurantData = response.body()!!
                restaurantList.addAll(restaurantData.data)
                setRestaurantAdapter(restaurantList)
            }

            override fun onFailure(call: Call<RestaurantData>, t: Throwable) {
                Toast.makeText(this@MainActivity, t.localizedMessage, Toast.LENGTH_SHORT).show()
            }

        })
    }
    fun setRestaurantAdapter(restaurantList: ArrayList<Restaurant>){
        val adapter = RestaurantsAdapter(restaurantList, onItemClick = {restaurant->
            val intent = Intent(this,MealActivity::class.java)
            intent.putExtra("id", restaurant.id)
            intent.putExtra("name", restaurant.name)
            startActivity(intent)
        })
        binding.rvRestaurants.adapter=adapter
    }

    private fun logout(){
        deleteSharedPreferences("main")
        startActivityAuth()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.logout){
            logout()
        }
        return super.onOptionsItemSelected(item)
    }
}





