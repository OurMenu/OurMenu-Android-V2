package com.kuit.ourmenu.data.service

import com.kuit.ourmenu.data.model.account.request.LoginRequest
import com.kuit.ourmenu.data.model.account.request.SignupRequest
import com.kuit.ourmenu.data.model.account.response.EmailResponse
import com.kuit.ourmenu.data.model.account.response.LoginResponse
import com.kuit.ourmenu.data.model.account.response.ReissueTokenResponse
import com.kuit.ourmenu.data.model.account.response.TemporaryPasswordResponse
import com.kuit.ourmenu.data.model.base.BaseResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AccountService {
    @POST("api/users/sign-up")
    fun signup(
        @Body request: SignupRequest
    ): BaseResponse<Unit>

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

    @POST("api/emails/temporary-password")
    fun sendTemporaryPassword(
        @Body email: String
    ): BaseResponse<TemporaryPasswordResponse>
}