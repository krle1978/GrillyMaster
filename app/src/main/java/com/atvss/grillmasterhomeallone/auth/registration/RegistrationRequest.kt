package com.atvss.grillmasterhomeallone.auth.registration

import com.google.gson.annotations.SerializedName

class RegistrationRequest (
    val email: String,
    val password: String,
    @SerializedName("password_confirmation")
    val confirmPassword: String
){
    fun isValid():Int{
        if (email.isEmpty()){
            return 1
        }
        if (!email.contains("@")){
            return 2
        }
        if (password.isEmpty()){
            return 3
        }
        if (password.length <= 6){
            return 4
        }

        if (confirmPassword.isEmpty()){
            return 6
        }
        if (confirmPassword.length <= 6){
            return 7
        }
        if (password!=confirmPassword){
            return 8
        }
        return -1
    }
}