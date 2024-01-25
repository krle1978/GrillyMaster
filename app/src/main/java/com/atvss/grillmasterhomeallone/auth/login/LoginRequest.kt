package com.atvss.grillmasterhomeallone.auth.login

class LoginRequest(
    val email:String,
    val password: String
){
    fun isValid(): Int{
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

        return  -1
    }
}