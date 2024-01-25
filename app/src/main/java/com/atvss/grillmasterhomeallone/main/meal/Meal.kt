package com.atvss.grillmasterhomeallone.main.meal
import com.atvss.grillmasterhomeallone.Picture

class Meal (
    val name: String,
    val price: Double,
    val picture: Picture
)

class MealData(
    val data: ArrayList<Meal>
)