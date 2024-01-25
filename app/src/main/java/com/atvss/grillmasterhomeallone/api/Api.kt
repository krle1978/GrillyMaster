package com.atvss.grillmasterhomeallone.api

import com.atvss.grillmasterhomeallone.auth.UserData
import com.atvss.grillmasterhomeallone.auth.login.LoginRequest
import com.atvss.grillmasterhomeallone.auth.registration.RegistrationRequest
import com.atvss.grillmasterhomeallone.main.meal.MealData
import com.atvss.grillmasterhomeallone.main.restaurant.RestaurantData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface Api {

    @POST("auth/login")
    fun login(@Body request: LoginRequest): Call<UserData>

    @POST("auth/register")
    fun register(@Body request: RegistrationRequest): Call<UserData>

    @GET("restourants")
    fun getRestaurants(): Call<RestaurantData>

    @GET("restourants/{id}/meals")
    fun getMeals(@Path("id") id: Int): Call<MealData>

}