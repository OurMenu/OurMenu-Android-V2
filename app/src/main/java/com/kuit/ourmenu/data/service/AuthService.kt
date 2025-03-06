package com.kuit.ourmenu.data.service

import com.kuit.ourmenu.data.model.auth.request.ConfirmCodeRequest
import com.kuit.ourmenu.data.model.auth.request.LoginRequest
import com.kuit.ourmenu.data.model.auth.request.SignupRequest
import com.kuit.ourmenu.data.model.auth.response.EmailResponse
import com.kuit.ourmenu.data.model.auth.response.LoginResponse
import com.kuit.ourmenu.data.model.auth.response.ReissueTokenResponse
import com.kuit.ourmenu.data.model.base.BaseResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("api/users/sign-up")
    fun signup(
        @Body request: SignupRequest
    ): BaseResponse<Unit>

    @POST("api/users/sign-out")
    fun logout(): BaseResponse<Unit>

    @POST("api/users/sign-in")
    fun login(
        @Body request: LoginRequest
    ): BaseResponse<LoginResponse>

    @POST("api/users/reissue-token")
    fun reissueToken(
        @Body refreshToken: String
    ): BaseResponse<ReissueTokenResponse>

    @POST("api/emails")
    fun sendEmail(
        @Body email: String
    ): BaseResponse<EmailResponse>

    @POST("api/emails/confirm-code")
    fun confirmCode(
        @Body request: ConfirmCodeRequest
    ): BaseResponse<Unit>
}