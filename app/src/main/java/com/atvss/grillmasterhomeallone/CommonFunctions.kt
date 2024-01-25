package com.atvss.grillmasterhomeallone

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.atvss.grillmasterhomeallone.auth.AuthActivity
import com.atvss.grillmasterhomeallone.auth.User
import com.atvss.grillmasterhomeallone.main.restaurant.MainActivity

fun Activity.saveUser(user: User){
    val sharedPrefs = getSharedPreferences("main", Context.MODE_PRIVATE)
    sharedPrefs.edit()
        .putInt("id",user.id)
        .putString("email", user.email)
        .apply()
}
fun Activity.isUserSaved():Boolean{
    val sharedPrefs = getSharedPreferences("main", Context.MODE_PRIVATE)
    val userId = sharedPrefs.getInt("id",-1)
    return userId != -1
}

fun Activity.startActivityMain(){
    val intent = Intent(this, MainActivity::class.java)
    startActivity(intent)
}

fun Activity.startActivityAuth(){
    val intent = Intent(this, AuthActivity::class.java)
    startActivity(intent)
    finish()
}

fun Activity.isInternetAvailable():Boolean{
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkCapabilities = connectivityManager.activeNetwork?:return false
    val activeNetwork = connectivityManager.getNetworkCapabilities(networkCapabilities)?:return false

    val hasConnection = when{
        activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)->true
        activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)->true
        activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)->true
        else->false
    }
    /*var hasInternet = false
    if (hasConnection){
        val address = InetAddress.getByName("www.google.com")
        hasInternet = !address.equals("")
    }*/

    return hasConnection
}