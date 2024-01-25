package com.atvss.grillmasterhomeallone.main.restaurant

import android.widget.ImageView
import com.atvss.grillmasterhomeallone.Picture
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.net.URL

class Restaurant(
    var id: Int,
    var name:String?,
    var rating: Double?=null,

    @SerializedName("delivery_price")
    val deliveryPrice: Double,
    val logo: Picture
)


class RestaurantData(
    val data: ArrayList<Restaurant>
)