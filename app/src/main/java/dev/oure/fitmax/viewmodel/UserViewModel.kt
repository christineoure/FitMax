package dev.oure.fitmax.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.oure.fitmax.models.LoginRequest
import dev.oure.fitmax.models.LoginResponse
import dev.oure.fitmax.models.RegisterRequest
import dev.oure.fitmax.models.RegisterResponse
import dev.oure.fitmax.repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {
    val userRepository = UserRepository()
    //Login request
    val loginLiveData = MutableLiveData<LoginResponse>()
    var loginErrorLiveData = MutableLiveData<String>()

    //register user request
    val registerLiveData = MutableLiveData<RegisterResponse>()
    val registerErrorLiveData = MutableLiveData<String>()

    fun login(loginRequest: LoginRequest){
        viewModelScope.launch {
            val response = userRepository.loginUser(loginRequest)
            if (response.isSuccessful){
                loginLiveData.postValue(response.body())
            }
            else {
                loginErrorLiveData.postValue(response.errorBody()?.string())
            }
        }
    }


    fun register(registerRequest: RegisterRequest) {
        viewModelScope.launch {
            val response = userRepository.registerUser(registerRequest)
            if (response.isSuccessful){
                registerLiveData.postValue(response.body())
            }else{
                registerErrorLiveData.postValue(response.errorBody()?.string())
            }
        }
    }
}