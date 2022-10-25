package dev.oure.fitmax.repository

import dev.oure.fitmax.api.APIClient
import dev.oure.fitmax.api.APIInterface
import dev.oure.fitmax.models.LoginRequest
import dev.oure.fitmax.models.LoginResponse
import dev.oure.fitmax.models.RegisterRequest
import dev.oure.fitmax.models.RegisterResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class UserRepository {
    val apiclient = APIClient.buildAPIClient(APIInterface::class.java)

    //   implementing the user Login network call
    suspend fun loginUser(loginRequest: LoginRequest): Response<LoginResponse> =
        withContext(Dispatchers.IO) {
            val response = apiclient.loginUser(loginRequest)
            return@withContext response
        }

    //implementing the User Register network call
    suspend fun registerUser(registerRequest: RegisterRequest) : Response<RegisterResponse> =
        withContext(Dispatchers.IO){
            val response = apiclient.registerUser(registerRequest)
            return@withContext response
        }
}