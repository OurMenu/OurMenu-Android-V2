package com.kuit.ourmenu.data.service

import com.kuit.ourmenu.data.model.account.request.SignupRequest
import com.kuit.ourmenu.data.model.base.BaseResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AccountService {
    @POST("api/users/sign-up")
    fun signup(
        @Body request: SignupRequest
    ): BaseResponse<Unit>
}