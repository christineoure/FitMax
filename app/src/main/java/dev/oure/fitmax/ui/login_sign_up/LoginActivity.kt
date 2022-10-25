package dev.oure.fitmax.ui.login_sign_up

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import dev.oure.fitmax.ui.home_and_splash.HomeActivity
import dev.oure.fitmax.databinding.ActivityLoginBinding
import dev.oure.fitmax.models.LoginRequest
import dev.oure.fitmax.models.LoginResponse
import dev.oure.fitmax.viewmodel.UserViewModel

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    lateinit var sharedPreferences: SharedPreferences
    val userViewModel : UserViewModel by viewModels()//instantiating the view model

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedPreferences = getSharedPreferences("FITMAX_PREFS", MODE_PRIVATE)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.tvSign.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        binding.btnLogIn.setOnClickListener {
            validateLogin()
        }
    }

    override fun onResume() {
        super.onResume()
        userViewModel.loginLiveData.observe(this, Observer{ loginResponse ->
            Toast.makeText(baseContext, loginResponse.message, Toast.LENGTH_LONG).show()
            persistLoginDetails(loginResponse)
            startActivity(Intent(baseContext, HomeActivity::class.java))
        })
        userViewModel.loginErrorLiveData.observe(this, Observer { errorMsg ->
            Toast.makeText(baseContext, errorMsg, Toast.LENGTH_LONG).show()
        })
    }

    fun validateLogin() {
        var email = binding.etEmail.text.toString()
        var password = binding.etPWD.text.toString()
        var error = false

        if (email.isBlank()) {
            error = true
            binding.tlEmail.error = "Email required"
        }

        if (password.isBlank()) {
            error = true
            binding.tlPWD.error = "Password required"
        }

        if (!error) {
            var loginRequest = LoginRequest(email, password)
            userViewModel.login(loginRequest)

        }
    }


    fun persistLoginDetails(loginResponse: LoginResponse) {
        val editor = sharedPreferences.edit()
        val token = "Bearer ${loginResponse.access_token}"
        editor.putString("USER_ID", loginResponse.user_id)
        editor.putString("ACCESS_TOKEN", token)
        editor.putString("PROFILE_ID", loginResponse.profile_id)
        editor.apply()

    }
}