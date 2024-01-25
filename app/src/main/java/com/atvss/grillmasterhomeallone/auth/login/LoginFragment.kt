package com.atvss.grillmasterhomeallone.auth.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.atvss.grillmasterhomeallone.R
import com.atvss.grillmasterhomeallone.api.Api
import com.atvss.grillmasterhomeallone.api.RetrofitClient
import com.atvss.grillmasterhomeallone.auth.UserData
import com.atvss.grillmasterhomeallone.auth.registration.RegistrationFragment
import com.atvss.grillmasterhomeallone.databinding.FragmentLoginBinding
import com.atvss.grillmasterhomeallone.isInternetAvailable
import com.atvss.grillmasterhomeallone.saveUser
import com.atvss.grillmasterhomeallone.startActivityMain
import retrofit2.Call
import retrofit2.Callback


import retrofit2.Response


class LoginFragment : Fragment() {

    private var binding: FragmentLoginBinding? = null

   override fun onCreateView(
       inflater: LayoutInflater,
       container: ViewGroup?,
       savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater,container,false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply{
            btnLogIn.setOnClickListener {
                onLoginClicked()
            }
            btnSignUp?.setOnClickListener {
                requireActivity().supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.authContainer, RegistrationFragment())
                    .commit()
            }
        }
    }
    private fun onLoginClicked(){
        binding?.apply {
            val email = edEmail.text.toString()
            val password = edPassword.text.toString()
            val request = LoginRequest(email,password)
            when(request.isValid()){
                1->{
                    showToast("Morate uneti email adresu")
                }
                2->{
                    showToast("Morate uneti validan email adresu")
                }
                3->{
                    showToast("Morate uneti password")
                }
                4->{
                    showToast("Morate uneti password duzi od 6 karaktera")
                }
                else->{
                    loginUser(request)
                    showToast("OK")
                }
            }
        }
    }
    private fun loginUser(request: LoginRequest){
        Log.d("ATVSS123","loginUser")
        if (requireActivity().isInternetAvailable()){
            Log.d("ATVSS123","Internet available")
            RetrofitClient.getClient().create(Api::class.java)
                .login(request).enqueue(object : Callback<UserData> {
                    override fun onResponse(call: Call<UserData>, response: Response<UserData>) {
                        if (response.body() == null){
                            Log.d("ATVSS123","data is null")
                            Log.d("ATVSS123","${response.code()}")
                            Log.d("ATVSS123","${response.message()}")
                            return
                        }
                        if (!response.isSuccessful){
                            Log.d("ATVSS123","no response")
                            return
                        }
                        response.body()?.data?.let{user->
                            requireActivity().apply {
                                saveUser(user)
                                startActivityMain()
                            }
                        }

                    }

                    override fun onFailure(call: Call<UserData>, t: Throwable) {
                        showToast("${t.localizedMessage}")
                    }

                })
        }else{
            showToast("Proverite vasu INTERNET konekciju")
        }

    }

    fun showToast(msg:String){
        Toast.makeText(requireActivity(), msg, Toast.LENGTH_SHORT).show()
    }
}