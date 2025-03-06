package com.kuit.ourmenu.data.service

import com.kuit.ourmenu.data.model.base.BaseResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST

interface UserService {

    @POST("api/emails/temporary-password")
    fun sendTemporaryPassword(
        @Body email: String
    ): BaseResponse<TemporaryPasswordResponse>

    @PATCH("api/users/password")
    fun changePassword(
        @Body request: ChangePasswordRequest
    ): BaseResponse<Unit>

    @PATCH("api/users/meal-times")
    fun updateMealTimes(
        @Body mealTimes: List<Int>
    ): BaseResponse<Unit>

    @GET("api/users")
    fun getUserInfo(): BaseResponse<UserInfoResponse>
}