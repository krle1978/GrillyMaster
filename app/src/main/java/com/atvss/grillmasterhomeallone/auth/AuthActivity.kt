package com.atvss.grillmasterhomeallone.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.atvss.grillmasterhomeallone.R
import com.atvss.grillmasterhomeallone.api.Api
import com.atvss.grillmasterhomeallone.api.RetrofitClient
import com.atvss.grillmasterhomeallone.auth.login.LoginFragment
import com.atvss.grillmasterhomeallone.auth.registration.RegistrationFragment
import com.atvss.grillmasterhomeallone.auth.registration.RegistrationRequest
import com.atvss.grillmasterhomeallone.databinding.FragmentLoginBinding
import com.atvss.grillmasterhomeallone.isUserSaved
import com.atvss.grillmasterhomeallone.main.restaurant.RestaurantData
import com.atvss.grillmasterhomeallone.startActivityMain
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        if (isUserSaved()) {
            startActivityMain()

        }else {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.authContainer, LoginFragment())
                .commit()
        }

    }

}