package com.kuit.ourmenu.data.service

import com.kuit.ourmenu.data.model.auth.request.LoginRequest
import com.kuit.ourmenu.data.model.auth.response.LoginResponse
import com.kuit.ourmenu.data.model.auth.response.TokenReIssueResponse
import com.kuit.ourmenu.data.model.base.BaseResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("api/users/sign-in")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ): BaseResponse<LoginResponse>

    @POST("api/users/reissue-token")
    suspend fun reIssueToken(
        @Body refreshToken: String
    ): BaseResponse<TokenReIssueResponse>
}