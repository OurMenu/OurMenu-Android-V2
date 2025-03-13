package com.kuit.ourmenu.data.service

import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("api/users/sign-in")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ): BaseResponse<LoginResponse>
}